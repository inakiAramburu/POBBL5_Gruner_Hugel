package gruner.huger.grunerhugel.simulation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.FarmHarvesterRepository;
import gruner.huger.grunerhugel.domain.repository.FarmPlowRepository;
import gruner.huger.grunerhugel.domain.repository.FarmSeederRepository;
import gruner.huger.grunerhugel.domain.repository.FarmTractorRepository;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.domain.repository.PlantRepository;
import gruner.huger.grunerhugel.domain.repository.TownRepository;
import gruner.huger.grunerhugel.domain.repository.WeatherRepository;
import gruner.huger.grunerhugel.domain.repository.WorkerRepository;
// import java.util.concurrent.BlockingQueue;
import gruner.huger.grunerhugel.model.Balance;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.simulation.thread.PlantThread;
import gruner.huger.grunerhugel.simulation.thread.TimeThread;
import gruner.huger.grunerhugel.simulation.thread.WeatherThread;

public class SimulationProcesses extends Thread implements PropertyChangeListener {
    public static final String DATA_UPDATE = "DATA UPDATE";
    // private static boolean horaConcluida = false;
    private WeatherRepository wRepository;
    private PlantRepository pRepository;
    private OptimalConditionsRepository oRepository;
    private WorkerRepository wkRepository;
    private static FarmHarvesterRepository fHarvRepository;
    private FarmPlowRepository fPlowRepository;
    private FarmSeederRepository fSeedRepository;
    private FarmTractorRepository fTractRepository;
    private LandRepository lRepository;
    private TownRepository tRepository;
    public static Farm farm;
    private boolean terminate;
    int hours = 0;
    int days = 0;
    Balance balance;
    WeatherThread wThread;
    PlantThread pThread;
    TimeThread tThread;
    // private Date actualDate;
    Land land;
    // BlockingQueue<String> queue;
    private Lock mutex;
    private Condition await;

    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public SimulationProcesses(Farm farm, WeatherRepository weatherRepository, PlantRepository plantRepository,
            OptimalConditionsRepository opCondRepository,
            WorkerRepository workerRepository, FarmHarvesterRepository fHarvRepository,
            FarmPlowRepository fPlowRepository, FarmSeederRepository fSeedRepository,
            FarmTractorRepository fTractRepository, LandRepository landRepository, TownRepository townRepository) {
        SimulationProcesses.farm = farm;
        this.wRepository = weatherRepository;
        this.oRepository = opCondRepository;
        this.pRepository = plantRepository;
        SimulationProcesses.fHarvRepository = fHarvRepository;
        this.fPlowRepository = fPlowRepository;
        this.fSeedRepository = fSeedRepository;
        this.fTractRepository = fTractRepository;
        this.lRepository = landRepository;
        this.tRepository = townRepository;
    }

    public void initialize(int initialBalance, Date startDate, Date endDate, List<Land> lands) {

        // -------------
        this.tThread = new TimeThread(startDate, endDate);
        this.balance = new Balance(initialBalance); // queue
        this.wThread = new WeatherThread(wRepository, startDate, lands);
        this.pThread = new PlantThread(lRepository, pRepository, oRepository, lands);
        // this.actualDate = new Date(finalDate.getTime() - startDate.getTime());
        // this.land = lands;
        this.tThread.addPropertyChangeListener(this);
        this.addPropertyChangeListener(wThread);
        // --------------
        this.terminate = false;
        this.mutex = new ReentrantLock();
        this.await = mutex.newCondition();
    }

    private void startThreads() {
        tThread.start();
        wThread.start();
        pThread.start();
    }

    private void joinThreads() {
        try {
            tThread.join();
            wThread.join();
            pThread.join();
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("All Threads Interrupted!");
            this.interrupt();
        }
    }

    @Override
    public void run() {
        startThreads();
        // code here
        while (!Thread.interrupted() && !terminate) {
            // no need
            try {
                await.await();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("SimulationProcesses Interrupted!");
                this.interrupt();
            }
        }
        joinThreads(); // in a while or in a listening
    }

    public static List<FarmHarvester> getHarvesters() {
        List<FarmHarvester> temp = new ArrayList<>();
        Iterable<FarmHarvester> it = fHarvRepository.findByFarm(farm);
        it.forEach(temp::add);
        return temp;
    }

    public int getHoras() {
        return hours;
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

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        String property = arg0.getPropertyName();
        switch (property) {
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
            // case TimeThread.TIME_RESUME:
            // break;
            case TimeThread.HOUR_PASS:
                this.pcs.firePropertyChange(DATA_UPDATE, null, arg0.getNewValue());
                break;
            case TimeThread.TERMINATE:
                terminate = true;
                this.pcs.firePropertyChange(TimeThread.TERMINATE, null, null);
                await.signal();
                break;
            default:
        }
    }
}
