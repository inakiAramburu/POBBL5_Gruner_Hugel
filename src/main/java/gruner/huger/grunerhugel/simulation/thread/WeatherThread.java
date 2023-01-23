package gruner.huger.grunerhugel.simulation.thread;

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

public class WeatherThread extends Thread {
    static final String WEATHER_CHECK = "WEATHER CHECK";
    private WeatherRepository wRepository;
    private static Map<String, Weather> forecast;
    Date date;
    List<String> villages;
    private static boolean check = false;
    private static boolean pause = false;
    private static Lock mutex;
    private static Condition checking;

    public WeatherThread(WeatherRepository wRepository, Date date, List<Land> lands) {
        this.wRepository = wRepository;
        forecast = new HashMap<>();
        initialize(date, lands);
        mutex = new ReentrantLock();
        checking = mutex.newCondition();
    }

    public void initialize(Date date, List<Land> lands) {
        this.date = date;
        this.villages = initializeVillages(lands);
        forecast.clear();
    }

    private List<String> initializeVillages(List<Land> lands) {
        List<String> temp = new ArrayList<>();
        lands.forEach(l -> temp.add(l.getTown().getName()));
        return temp;
    }

    @Override
    public void run() {
        GrunerhugelApplication.logger.log(Level.INFO, "WeatherThread Id: {0}", this.getId());

        while (!pause) {
            if (check) {
                updateWeather();
                PlantThread.callSignal();
                check = false;
            }
            awaitCheck();
        }
    }

    private void updateWeather() {
        date = TimeThread.getActualDate();
        List<Weather> list = findAllRelevantWeathers();
        if (!list.isEmpty() || list != null) {
            list.forEach(w -> forecast.put(w.getTown().getName(), w));
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

    public static void pause() {
        pause = true;
    }
}
