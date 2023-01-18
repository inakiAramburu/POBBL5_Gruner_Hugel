package gruner.huger.grunerhugel.simulation.thread;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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

public class WeatherThread extends Thread implements PropertyChangeListener {
    static final String WEATHER_CHECK = "WEATHER CHECK";
    private WeatherRepository wRepository;
    private static Map<String, Weather> forecast;
    Date date;
    List<String> villages;
    private PropertyChangeSupport pcs;
    private static boolean check = false;
    private static Lock mutex;
    private static Condition checking;
    // private static boolean pause;

    public WeatherThread(WeatherRepository wRepository, Date date, List<Land> lands) {
        this.wRepository = wRepository;
        forecast = new HashMap<>();
        initialize(date, lands);
        pcs = new PropertyChangeSupport(this);
        mutex = new ReentrantLock();
        checking = mutex.newCondition();
    }

    public void initialize(Date date, List<Land> lands) { // to change all about date and lands
        this.date = date; // change date
        this.villages = initializeVillages(lands); // change village names
        forecast.clear(); // clear map
    }

    private List<String> initializeVillages(List<Land> lands) { // take village names out from lands
        List<String> temp = new ArrayList<>();
        lands.forEach(l -> temp.add(l.getTown().getName()));
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
        GrunerhugelApplication.logger.log(Level.INFO, "WeatherThread Id: {0}", this.getId());

        while (!Thread.interrupted()) {
            if (check) {
                GrunerhugelApplication.logger.info("UPDATE WEATHER");
                updateWeather();
                PlantThread.callSignal();
                // notifyListeners(WEATHER_CHECK, forecast);
                check = false;
            }
            awaitCheck();
        }
    }

    private void updateWeather() {
        date = TimeThread.getActualDate();
        List<Weather> list = findAllRelevantWeathers(); // get all weather from repository with date
        if (!list.isEmpty() || list != null) {
            list.forEach(w -> forecast.put(w.getTown().getName(), w)); // take only
        }
    }

    private void awaitCheck() {
        while (!check) {
            mutex.lock();
            try {
                checking.await();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("WeatherThread Interrupted!");
                this.interrupt();
            } finally {
                mutex.unlock();
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
            case TimeThread.HOUR_PASS:
                check = true;
                if (check) {
                    mutex.lock();
                    try {
                        this.date = (Date) arg0.getNewValue();
                        checking.signal();
                    } finally {
                        mutex.unlock();
                    }
                }
                break;
            case TimeThread.TIME_PAUSE:
                this.pcs.firePropertyChange(TimeThread.TIME_PAUSE, null, null);
                try {
                    GrunerhugelApplication.logger.info("Latch WeatherThread");
                    TimeThread.cDownLatch.await();
                } catch (InterruptedException e) {
                    GrunerhugelApplication.logger.warning("CountDown Interrupted!");
                    this.interrupt();
                }
                break;
            default:
        }
    }

    public static void callSignal() {
        mutex.lock();
        try{
        check = true;
        checking.signal();
        } finally {
            mutex.unlock();
        }
    }

    public static Map<String, Weather> getForecast() {
        return forecast;
    }
}
