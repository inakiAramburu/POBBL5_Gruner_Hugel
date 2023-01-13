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
import java.util.concurrent.CountDownLatch;
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
    static final String WEATHER_CHECK = "WEATHER CHECK";
    private WeatherRepository wRepository;
    Map<String, Weather> forecast;
    Date date;
    List<String> villages;
    private PropertyChangeSupport pcs;
    private boolean check;
    private Lock mutex;
    private Condition checking;

    public WeatherThread(WeatherRepository wRepository, Date date, List<Land> lands) {
        this.wRepository = wRepository;
        forecast = new HashMap<>();
        initialize(date, lands);
        pcs = new PropertyChangeSupport(this);
        this.mutex = new ReentrantLock();
        this.checking = this.mutex.newCondition();
    }

    public void initialize(Date date, List<Land> lands) { // to change all about date and lands
        this.date = date; // change date
        this.villages = initializeVillages(lands); // change village names
        forecast.clear(); // clear map
    }

    private List<String> initializeVillages(List<Land> lands) { // take village names out from lands
        List<String> temp = new ArrayList<>();
        lands.forEach(l -> temp.add(l.getTown().getName()));
        // temp.add("Adios");
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
            check = false;
            while (!Thread.interrupted()) {
                awaitCheck();
                if (check) {
                    updateWeather();
                    notifyListeners(WEATHER_CHECK, forecast);
                    check = false;
                }
            }
        } finally {
            mutex.unlock();
        }
    }

    private void notifyListeners(String codeName, Object newValue) {
        this.pcs.firePropertyChange(codeName, null, newValue);
    }

    private void updateWeather() {
        List<Weather> list = findAllRelevantWeathers(); // get all weather from repository with date
        list.forEach(w -> forecast.put(w.getTown().getName(), w)); // take only
        // forecast.keySet().forEach(f -> GrunerhugelApplication.logger.info(f.toString()));
    }

    private void awaitCheck() {
        while (!check) {
            try {
                // this.yield();
                checking.await();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("WeatherThread Interrupted!");
                this.interrupt();
            }
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
        String property = arg0.getPropertyName();
        switch (property) {
            case SimulationProcesses.DATA_UPDATE:
                check = true;
                if (check) {
                    mutex.lock();
                    try {
                        this.date = (Date) arg0.getNewValue();
                        checking.signal();
                        GrunerhugelApplication.logger.info("weather signal");
                    } finally {
                        mutex.unlock();
                    }
                }
                break;
            case TimeThread.TIME_PAUSE:
                this.pcs.firePropertyChange(TimeThread.TIME_PAUSE, null, arg0.getNewValue());
                CountDownLatch cLatch = (CountDownLatch) arg0.getNewValue();
                try {
                    cLatch.await();
                } catch (InterruptedException e) {
                    GrunerhugelApplication.logger.warning("CountDown Interrupted!");
                    this.interrupt();
                }
                break;
            default:
        }
    }
}
