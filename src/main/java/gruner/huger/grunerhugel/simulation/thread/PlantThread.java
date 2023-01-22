package gruner.huger.grunerhugel.simulation.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.domain.repository.PlantRepository;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.OptimalConditions;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Weather;
import gruner.huger.grunerhugel.simulation.enumeration.PlantType;

public class PlantThread extends Thread {
    static final String PROPERTY_NAME = "PLANT_THREAD";
    private static Map<Land, List<Plant>> fields;
    private List<Land> lands;
    private static boolean check = false;
    private static boolean pause = false;
    private static Lock mutex;
    private static Condition checking;
    private static PlantRepository pRepository;
    private static OptimalConditionsRepository oRepository;

    public PlantThread(PlantRepository plantRepository, OptimalConditionsRepository opCondRepository,
            List<Land> lands) {
        this.lands = lands;
        fields = new HashMap<>();
        mutex = new ReentrantLock();
        checking = mutex.newCondition();
        pRepository = plantRepository;
        oRepository = opCondRepository;
        updateMap();
    }

    public static void addPlant(Land land, PlantType pType) {
        OptimalConditions oConditions = oRepository.findByName(pType.getPlantType());
        Plant plant = new Plant(oConditions, land);
        List<Plant> list = fields.get(land);
        if(list == null) list = new ArrayList<>();
        list.add(plant);
        fields.put(land, list);
    }

    private void updateMap() {
        for (Land land : lands) {
            List<Plant> plants = pRepository.findByLand(land);
            fields.put(land, plants);
        }
    }

    @Override
    public void run() {
        mutex.lock();
        try {
            while (!pause) {
                awaitCheck();
                updatePlants();
                LandThread.callSignal();
            }
            savePlants();
        } finally {
            mutex.unlock();
        }
    }

    private void updatePlants() {
        Map<String, Weather> forecast = WeatherThread.getForecast();
        List<Integer> deadList = new ArrayList<>();
        for (List<Plant> list : fields.values()) {
            deadList.addAll(checkPlants(list, forecast));
        }
        if (!deadList.isEmpty()) {
            eliminateDeadPlants(deadList);
            deadList.clear();
        }
        check = false;
    }

    private List<Integer> checkPlants(List<Plant> list, Map<String, Weather> forecast) {
        List<Integer> deadList = new ArrayList<>();
        for (Plant p : list) {
            p.checkOptimalCondition(forecast.get(p.getLand().getTown().getName()));
            if (p.getHealthPoint() <= 0) {
                deadList.add(p.getId());
            }
        }
        return deadList;
    }

    private void eliminateDeadPlants(List<Integer> deadList) {
        pRepository.deleteAllById(deadList);
    }

    private void awaitCheck() {
        try {
            while (!check) {
                checking.await();
            }
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("PlantThread Interrupted!");
            this.interrupt();
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

    public void savePlants(){
        List<Plant> all = new ArrayList<>();
        fields.values().forEach(all::addAll);
        pRepository.saveAll(all);
    }

    public static Map<Land, List<Plant>> getLandAndPlantList() {
        return fields;
    }

    public static void pause() {
        pause = true;
    }
}
