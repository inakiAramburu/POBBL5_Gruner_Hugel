package gruner.huger.grunerhugel.simulation.thread;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.WeatherRepository;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Weather;
import gruner.huger.grunerhugel.simulation.SimulationProcesses;

public class WeatherThread extends Thread implements PropertyChangeListener {
    static final String PROPERTY_NAME = "WEATHER_THREAD";
    private WeatherRepository wRepository;
    Map<String, Weather> forecast;
    String date;
    List<String> villages;
    private PropertyChangeSupport pcs;
    private boolean simCheck;
    private Lock mutex;
    private Condition check;

    public WeatherThread(WeatherRepository wRepository, Date date, Land lands) {
        this.wRepository = wRepository;
        forecast = new HashMap<>();
        initialize(date, lands);
        pcs = new PropertyChangeSupport(this);
        this.mutex = new ReentrantLock();
        this.check = this.mutex.newCondition();
    }

    public void initialize(Date date, Land lands) { // to change all about date and lands
        this.date = convertDate(date); // change date
        this.villages = initializeVillages(lands); // change village names
        forecast.clear(); // clear map
    }

    private String convertDate(Date tempDate) { // convert from date to string
        DateFormat df = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
        return df.format(tempDate);
    }

    private List<String> initializeVillages(Land lands) { // take village names out from lands
        List<String> temp = new ArrayList<>();
        // lands.forEach(l -> temp.add(l.getTown().getName()));
        temp.add("Adios");
        return temp;
    }

    // public void initialize(int hoursPassed, List<Land> lands){ // to change all
    // about lands and add N hours to Date
    // date.setTime(date.getTime() + hoursPassed*3600000); // 3600000 = 1000ms * 60s
    // * 60min
    // this.villages = initializeVillages(lands); // change village names
    // forecast.clear(); // clear map
    // }

    @Override
    public void run() {
        mutex.lock();
        try {
            simCheck = false;
            while (!this.interrupted()) {
                while (!simCheck) {
                    check.await();
                }
                if (simCheck) {
                    List<Weather> list = findAllRelevantWeathers(); // get all weather from repository with date
                    list.forEach(w -> forecast.put(w.getTown().getName(), w)); // take only
                    forecast.keySet().forEach(f -> GrunerhugelApplication.logger.info(f.toString()));
                    this.pcs.firePropertyChange(PROPERTY_NAME, null, forecast);
                    simCheck = false;
                }
            }
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("WeatherThread Interrupted!");
            this.interrupt();
        } finally {
            mutex.unlock();
        }
    }

    private List<Weather> findAllRelevantWeathers() {
        List<Weather> temp = new ArrayList<>();
        villages.forEach(v -> temp.add(wRepository.findByVillageAndWeather(v, date)));
        return temp;
    }

    public Weather getWeatherFromTownNameAndDate(String townName) {
        return forecast.get(townName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        if (arg0.getPropertyName().equals(SimulationProcesses.PROPERTY_NAME)) {
            simCheck = (boolean) arg0.getOldValue();
            if (simCheck) {
                mutex.lock();
                try{
                    check.signal();
                    GrunerhugelApplication.logger.info("weather signal");
                }finally{
                    mutex.unlock();
                }
            }
        }

    }
}
