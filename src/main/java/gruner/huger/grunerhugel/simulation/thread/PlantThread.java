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
    private static Map<Land, List<Plant>> fields = new HashMap<>();
    private static List<Land> lands;
    private static boolean check = false;
    private static boolean pause = false;
    private static Lock mutex = new ReentrantLock();
    private static Condition checking = mutex.newCondition();
    private static PlantRepository pRepository;
    private static OptimalConditionsRepository oRepository;

    public PlantThread(PlantRepository plantRepository, OptimalConditionsRepository opCondRepository,
            List<Land> farmLands) {
        lands = farmLands;
        pRepository = plantRepository;
        oRepository = opCondRepository;
        updateMap();
    }

    public static void addPlant(Land land, PlantType pType, int numSeed) {
        OptimalConditions oConditions = oRepository.findByName(pType.getPlantType());
        List<Plant> list = fields.get(land);
        if (list == null)
            list = new ArrayList<>();
        Plant plant;
        for (int i = 0; i < numSeed; i++) {
            plant = new Plant(oConditions, land);
            list.add(plant);
        }
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
        GrunerhugelApplication.logger.info("Plant PAUSE");
    }

    private void updatePlants() {
        updateLands();
        Map<String, Weather> forecast = WeatherThread.getForecast();
        List<Integer> deadList = new ArrayList<>();
        for (List<Plant> list : fields.values()) {
            if (list != null && !list.isEmpty()) {
                deadList.addAll(checkPlants(list, forecast));
            }
        }
        if (!deadList.isEmpty()) {
            eliminateDeadPlants(deadList);
            deadList.clear();
        }
        check = false;
    }

    private void updateLands() {
        List<Land> list = LandThread.getLands();
        for (Land land : list) {
            if (!fields.containsKey(land)) {
                updateLand(land);
            }
        }
        lands = list;
    }

    private void updateLand(Land land) {
        Land temp = isLandInList(land, new ArrayList<>(fields.keySet()));
        if (temp != null) {
            List<Plant> plants = fields.remove(temp);
            fields.put(land, plants);
        }
    }

    private Land isLandInList(Land land, List<Land> list) {
        boolean found = false;
        int kont;
        for (kont = 0; kont < list.size() && !found; kont++) {
            if (list.get(kont).getSize() == land.getSize() && list.get(kont).getLatitude() == land.getLatitude()
                    && list.get(kont).getLongitude() == land.getLongitude()) {
                found = true;
            }
        }
        return (found) ? list.get(kont - 1) : null;
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

    public static void savePlants() {
        List<Plant> old = new ArrayList<>();
        for (Land land : lands) {
            old.addAll(pRepository.findByLand(land));
        }
        List<Plant> newList = new ArrayList<>();
        for (List<Plant> list : fields.values()) {
            if (list != null && !list.isEmpty()) {
                newList.addAll(list);
            }
        }
        for(int i=0;i<old.size();i++){
            for(int j=0;j<newList.size();j++){
                if(old.get(i).getId() == newList.get(j).getId()){
                    old.get(i).setGrowthRate(newList.get(j).getGrowthRate());
                    old.get(i).setHealthPoint(newList.get(j).getHealthPoint());
                    old.get(i).setStatus(newList.get(j).getStatus());
                }
            }
        }
        pRepository.saveAll(old);
    }

    public static Map<Land, List<Plant>> getLandAndPlantList() {
        return fields;
    }

    public static void pause() {
        savePlants();
        pause = true;
        callSignal();
    }
}
