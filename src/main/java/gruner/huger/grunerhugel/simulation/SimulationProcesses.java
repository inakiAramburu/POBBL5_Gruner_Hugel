package gruner.huger.grunerhugel.simulation;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.FarmTractorRepository;
import gruner.huger.grunerhugel.domain.repository.FuelRepository;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.domain.repository.PlantRepository;
import gruner.huger.grunerhugel.domain.repository.SimulationRepository;
import gruner.huger.grunerhugel.domain.repository.WeatherRepository;
import gruner.huger.grunerhugel.domain.repository.WheatPriceRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.simulation.thread.Balance;
import gruner.huger.grunerhugel.simulation.thread.FuelThread;
import gruner.huger.grunerhugel.simulation.thread.LandThread;
import gruner.huger.grunerhugel.simulation.thread.PlantThread;
import gruner.huger.grunerhugel.simulation.thread.TimeThread;
import gruner.huger.grunerhugel.simulation.thread.WeatherThread;
import gruner.huger.grunerhugel.simulation.thread.WheatPriceThread;

public class SimulationProcesses extends Thread {
    public static final String DATA_UPDATE = "DATA UPDATE";
    private WeatherRepository wRepository;
    private PlantRepository pRepository;
    private OptimalConditionsRepository oRepository;
    private FarmTractorRepository fTractRepository;
    private LandRepository lRepository;
    private FuelRepository fRepository;
    private WheatPriceRepository wpRepository;
    private static FarmRepository faRepository;
    private static Farm farm;
    private Balance balance;
    WeatherThread wThread;
    PlantThread pThread;
    TimeThread tThread;
    FuelThread fThread;
    WheatPriceThread wpThread;
    LandThread lThread;
    private static boolean pause = false;
    private static Lock mutex = new ReentrantLock();
    private static Condition cond = mutex.newCondition();

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

    public void constructVehicleRepositories(FarmTractorRepository farmTractRepository) {
        this.fTractRepository = farmTractRepository;
    }

    public void initialize(double initBalance, Date startDate, Date endDate, FarmRepository farmRepository, SimulationRepository simulationRepository) {
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();
        faRepository = farmRepository;
        // -------------
        this.tThread = new TimeThread(startDate, endDate, simulationRepository);
        this.balance = new Balance(initBalance, blockingQueue); // queue
        this.wThread = new WeatherThread(wRepository, startDate, farm.getLands());
        this.pThread = new PlantThread(pRepository, oRepository, farm.getLands());
        this.fThread = new FuelThread(fRepository);
        this.wpThread = new WheatPriceThread(wpRepository);
        this.lThread = new LandThread(lRepository, blockingQueue, fTractRepository);
    }

    private void startThreads() {
        wThread.start();
        pThread.start();
        fThread.start();
        wpThread.start();
        lThread.start();
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
            lThread.join();
            balance.join();
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("All Threads Interrupted!");
            this.interrupt();
        }
    }

    @Override
    public void run() {
        startThreads();
        while (!pause) {
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

    public static Farm getFarm() {
        return farm;
    }

    public static void setMoney(double money){
        farm.setMoney(money);
        faRepository.save(farm);
    }

    public static void pause() {
        mutex.lock();
        try {
            pause = true;
            cond.signal();
        } finally {
            mutex.unlock();
        }
    }
}
