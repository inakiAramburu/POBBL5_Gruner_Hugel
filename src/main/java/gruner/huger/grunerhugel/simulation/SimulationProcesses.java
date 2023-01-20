package gruner.huger.grunerhugel.simulation;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.FarmHarvesterRepository;
import gruner.huger.grunerhugel.domain.repository.FarmPlowRepository;
import gruner.huger.grunerhugel.domain.repository.FarmSeederRepository;
import gruner.huger.grunerhugel.domain.repository.FarmTractorRepository;
import gruner.huger.grunerhugel.domain.repository.FuelRepository;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.domain.repository.PlantRepository;
import gruner.huger.grunerhugel.domain.repository.WeatherRepository;
import gruner.huger.grunerhugel.domain.repository.WheatPriceRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.simulation.thread.Balance;
import gruner.huger.grunerhugel.simulation.thread.FuelThread;
import gruner.huger.grunerhugel.simulation.thread.PlantThread;
import gruner.huger.grunerhugel.simulation.thread.TimeThread;
import gruner.huger.grunerhugel.simulation.thread.WeatherThread;
import gruner.huger.grunerhugel.simulation.thread.WheatPriceThread;
import gruner.huger.grunerhugel.simulation.thread.WorkerThread;

public class SimulationProcesses extends Thread {
    public static final String DATA_UPDATE = "DATA UPDATE";
    private WeatherRepository wRepository;
    private PlantRepository pRepository;
    private OptimalConditionsRepository oRepository;
    private static FarmHarvesterRepository fHarvRepository;
    private FarmPlowRepository fPlowRepository;
    private FarmSeederRepository fSeedRepository;
    private FarmTractorRepository fTractRepository;
    private LandRepository lRepository;
    private FuelRepository fRepository;
    private WheatPriceRepository wpRepository;
    private static Farm farm;
    private static boolean terminate = false;
    int hours = 0;
    int days = 0;
    private Balance balance;
    private WeatherThread wThread;
    private PlantThread pThread;
    private TimeThread tThread;
    private FuelThread fThread;
    private WheatPriceThread wpThread;
    private WorkerThread wkThread;
    private static Lock mutex = new ReentrantLock();
    private static Condition cond = mutex.newCondition();;

    public SimulationProcesses(Farm apointedFarm, WeatherRepository weatherRepository, PlantRepository plantRepository,
            OptimalConditionsRepository opCondRepository, LandRepository landRepository, FuelRepository fuelRepository,
            WheatPriceRepository wPriceRepository) {
        farm = apointedFarm;
        this.wRepository = weatherRepository;
        this.oRepository = opCondRepository;
        this.pRepository = plantRepository;
        this.lRepository = landRepository;
        this.fRepository = fuelRepository;
        this.wpRepository = wPriceRepository;
    }

    public void constructVehicleRepositories(FarmHarvesterRepository fHarvRepository,
            FarmPlowRepository fPlowRepository,
            FarmSeederRepository fSeedRepository, FarmTractorRepository fTractRepository) {
        SimulationProcesses.fHarvRepository = fHarvRepository;
        this.fPlowRepository = fPlowRepository;
        this.fSeedRepository = fSeedRepository;
        this.fTractRepository = fTractRepository;
    }

    public void initialize(int initialBalance, Date startDate, Date endDate, List<Land> lands) {
        BlockingQueue<Message> blockingQueue = new ArrayBlockingQueue<>(1000);
        // -------------
        this.tThread = new TimeThread(startDate, endDate);
        this.balance = new Balance(initialBalance, blockingQueue); // queue
        this.wThread = new WeatherThread(wRepository, startDate, lands);
        this.pThread = new PlantThread(lRepository, pRepository, oRepository, lands);
        this.fThread = new FuelThread(fRepository);
        this.wpThread = new WheatPriceThread(wpRepository);
        this.wkThread = new WorkerThread(blockingQueue);
    }

    private void startThreads() {
        wThread.start();
        pThread.start();
        fThread.start();
        wpThread.start();
        wkThread.start();
        balance.start();
        tThread.start();
    }

    private void joinThreads() {
        try {
            tThread.join();
            wThread.join();
            pThread.join();
            fThread.join();
            wpThread.join();
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("All Threads Interrupted!");
            this.interrupt();
        }
    }

    @Override
    public void run() {
        startThreads();
        while (!Thread.interrupted() && !terminate) {
            mutex.lock();
            try {
                cond.await();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("SimulationProcesses Interrupted!");
                this.interrupt();
            } finally {
                mutex.unlock();
            }
        }
        joinThreads();
    }

    public int getHoras() {
        return hours;
    }

    public void setRepositories(WeatherRepository wRepository) {
        this.wRepository = wRepository;
    }

    public static Farm getFarm() {
        return farm;
    }

    public static void callSignal() {
        mutex.lock();
        try {
            terminate = true;
            cond.signal();
        } finally {
            mutex.unlock();
        }
    }
}
