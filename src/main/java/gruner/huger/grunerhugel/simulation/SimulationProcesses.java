package gruner.huger.grunerhugel.simulation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.Date;
import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.domain.repository.WeatherRepository;
// import java.util.concurrent.BlockingQueue;
import gruner.huger.grunerhugel.model.Balance;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.OptimalConditions;
import gruner.huger.grunerhugel.model.Weather;
import gruner.huger.grunerhugel.simulation.thread.PlantThread;
import gruner.huger.grunerhugel.simulation.thread.PlantType;
import gruner.huger.grunerhugel.simulation.thread.WeatherThread;

public class SimulationProcesses extends Thread {
    public static final String PROPERTY_NAME = "SIMULATION";
    static final int HOUR_DURATION = 1250; // miliseconds
    static final int HOURS_DAY = 24; // hours
    // private static boolean horaConcluida = false;
    private WeatherRepository wRepository;
    private OptimalConditionsRepository oRepository;
    int hours = 0, days = 0;
    int accelerator = 1;
    Balance balance;
    WeatherThread wThread;
    PlantThread pThread;
    // private Date actualDate;
    Land land;
    // BlockingQueue<String> queue;

    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public SimulationProcesses(int initialBalance, WeatherRepository weatherRepository,
            OptimalConditionsRepository oRepository) {
        this.wRepository = weatherRepository;
        this.oRepository = oRepository;
        this.balance = new Balance(initialBalance); // queue
    }

    public void initialize(Date startDate, Date finalDate, Land lands) {
        this.wThread = new WeatherThread(wRepository, startDate, lands);
        this.pThread = new PlantThread(Arrays.asList(lands), oRepository);
        // this.actualDate = new Date(finalDate.getTime() - startDate.getTime());
        this.land = lands;
        this.addPropertyChangeListener(wThread);
    }
    
    private void startThreads() {
        wThread.start();
        pThread.start();
    }

    @Override
    public void run() {
        startThreads();
        try {
            // while(actualDate)
            // while (days != getMonthDays()) {
            // while (hours != HOURS_DAY) {
            Thread.sleep(HOUR_DURATION / accelerator);
            this.pcs.firePropertyChange(PROPERTY_NAME, true, null);
            Weather w = null;
            while (w == null) {
                w = wThread.getWeatherFromTownNameAndDate("Adios");
                GrunerhugelApplication.logger.info("null");
            }
            pThread.addNewPlant(land, PlantType.WHEAT);
            GrunerhugelApplication.logger.info(w.toString());
            // GrunerhugelApplication.logger.log(Level.INFO,"Hora: {0}.", hours);
            hours++;
            // }
            // }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getHoras() {
        return hours;
    }

    public int getAccelerator() {
        return accelerator;
    }

    public void setAccelerator(int accelerator) {
        this.accelerator = accelerator;
    }

    public void setRepositories(WeatherRepository wRepository) {
        this.wRepository = wRepository;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
