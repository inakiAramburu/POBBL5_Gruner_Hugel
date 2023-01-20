package gruner.huger.grunerhugel.simulation.thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.domain.repository.PlantRepository;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.OptimalConditions;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Weather;
import gruner.huger.grunerhugel.simulation.SimulationProcesses;
import gruner.huger.grunerhugel.simulation.enumeration.PlantType;

public class PlantThread extends Thread {
    static final String PROPERTY_NAME = "PLANT_THREAD";
    private static Map<Land, List<Plant>> fields;
    private List<Land> lands;
    private static boolean check = false;
    private static Lock mutex;
    private static Condition checking;
    private LandRepository lRepository;
    private static PlantRepository pRepository;
    private static OptimalConditionsRepository oRepository;

    public PlantThread(LandRepository landRepository, PlantRepository plantRepository,
            OptimalConditionsRepository opCondRepository, List<Land> lands) {
        this.lands = lands;
        fields = new HashMap<>();
        mutex = new ReentrantLock();
        checking = mutex.newCondition();
        this.lRepository = landRepository;
        pRepository = plantRepository;
        oRepository = opCondRepository;
        updateMap();
    }

    public static void addPlant(Land land, PlantType pType) {
        OptimalConditions oConditions = oRepository.findByName(pType.getPlantType());
        Plant plant = new Plant(oConditions, land);
        pRepository.save(plant);
    }

    private void updateMap() {
        updateLands();
        for (Land land : lands) {
            List<Plant> plants = pRepository.findByLand(land);
            fields.put(land, plants);
        }
    }

    @Override
    public void run() {
        mutex.lock();
        try {
            while (!Thread.interrupted()) {
                awaitCheck();
                updatePlants();
                WorkerThread.callSignal();
            }
        } finally {
            mutex.unlock();
        }
    }

    private void updatePlants() {
        Map<String, Weather> forecast = WeatherThread.getForecast();
        for (Land land : lands) {
            List<Plant> plants = fields.get(land);
            if (plants != null)
                for (Plant plant : plants) {
                    plant.checkOptimalCondition(forecast.get(land.getTown().getName()));
                    GrunerhugelApplication.logger.log(Level.INFO, "Plant health: {0}", plant.getHealthPoint());
                }
        }
        check = false;
    }

    private void updateLands() {
        lands = (List<Land>) lRepository.findByFarm(SimulationProcesses.getFarm());
    }

    private void awaitCheck() {
        while (!check) {
            try {
                checking.await();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("PlantThread Interrupted!");
                this.interrupt();
            }
        }
    }

    public static void callSignal() {
        mutex.lock();
        try {
            check = true;
            checking.signal();
        } finally {
            mutex.unlock();
        }
    }

    public static Map<Land,List<Plant>> getLandAndPlantList(){
        return fields;
    }
}
