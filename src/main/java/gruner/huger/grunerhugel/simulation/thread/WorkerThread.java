package gruner.huger.grunerhugel.simulation.thread;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Worker;
import gruner.huger.grunerhugel.simulation.Message;
import gruner.huger.grunerhugel.simulation.SimulationProcesses;
import gruner.huger.grunerhugel.simulation.enumeration.LandStatus;

public class WorkerThread extends Thread {
    private static final int PORCENTAJE = 15;
    private Map<Land, List<Worker>> assignationMap;
    private List<Land> lands;
    private List<Worker> workers;
    private static boolean check;
    // private static boolean workingHours;
    private static Lock mutex;
    private static Condition checking;
    private static BlockingQueue<Message> queue;
    private Random rand;

    public WorkerThread(BlockingQueue<Message> blockingQueue) {
        queue = blockingQueue;
        this.assignationMap = new HashMap<>();
        this.workers = setWorkers();
        this.lands = new ArrayList<>();
        mutex = new ReentrantLock();
        checking = mutex.newCondition();
        assignWorkersToLands();
        rand = new SecureRandom();
    }

    private List<Worker> setWorkers() {
        List<Worker> temp = new ArrayList<>();
        for (int i = 0; i < SimulationProcesses.getFarm().getNumWorkers(); i++) {
            temp.add(new Worker(queue));
        }
        return temp;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            awaitCheck();
            updateLandStatus(); // cada hora bien
            work(); // cada hora
            check = false;
        }
    }

    private void work() {
        for (Entry<Land, List<Worker>> lEntry : assignationMap.entrySet()) {
            if (lEntry.getValue().get(0).getCount() != -1) {
                int workHours = calculateWorkHours(lEntry.getKey().getSize(),
                        getLandStatus(lEntry.getKey().getStatus()),
                        lEntry.getValue().size()); // faltan los vehiculos
                for (Worker w : lEntry.getValue()) {
                    w.setWork(workHours);
                    w.start();
                }
            }
        }
    }

    private int calculateWorkHours(double size, LandStatus status, int numWorkers) {
        double totalCost = size * status.getCost();
        if (numWorkers > 1) {
            totalCost -= numWorkers * 1.5;
        }
        // vehicles
        return (int) Math.ceil(totalCost);
    }

    private LandStatus getLandStatus(String status) {
        if (status.equals(LandStatus.PLANTING.getStatus())) {
            return LandStatus.PLANTING;
        } else if (status.equals(LandStatus.HARVESTING.getStatus())) {
            return LandStatus.HARVESTING;
        } else {
            return LandStatus.GROWING;
        }
    }

    private void awaitCheck() {
        while (!check) {
            mutex.lock();
            try {
                checking.await();
                hoursPass();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("WeatherThread Interrupted!");
                this.interrupt();
            } finally {
                mutex.unlock();
            }
        }
    }

    private void hoursPass() {
        for (List<Worker> lList : assignationMap.values()) {
            lList.forEach(Worker::hourPass);
        }
    }

    private void updateLandStatus() {
        Map<Land, List<Plant>> landPlantList = PlantThread.getLandAndPlantList();
        lands = new ArrayList<>(landPlantList.keySet());
        for (Land land : lands) {
            List<Plant> plants = getPercentPlants(landPlantList.get(land));
            String status = getPlantsStatus(plants);
            land.setStatus(status);
        }
    }

    private String getPlantsStatus(List<Plant> plants) {
        if (plants == null || plants.isEmpty()) {
            return LandStatus.EMPTY.getStatus();
        }
        Map<LandStatus, Integer> sMap = new HashMap<>();
        for (Plant plant : plants) {
            LandStatus lStatus = getLandStatusFromPlant(plant.getStatus());
            Integer count = sMap.get(lStatus);
            if (count == null) {
                count = 0;
            }
            count++;
            sMap.put(lStatus, count);
        }
        return getMayorStatus(sMap);
    }

    private String getMayorStatus(Map<LandStatus, Integer> map) {
        List<LandStatus> list = new ArrayList<>(map.keySet());
        LandStatus mayor = list.get(0);
        for (LandStatus status : list) {
            if (map.get(status) > map.get(mayor)) {
                mayor = status;
            }
        }
        return mayor.getStatus();
    }

    private LandStatus getLandStatusFromPlant(String status) {
        LandStatus lStatus;
        switch (status) {
            case "Growing":
            case "VEGETATIVE":
            case "TILLERING":
            case "ANTHESIS":
            case "MILKY":
            case "PASTY":
            case "MATURATION":
                lStatus = LandStatus.GROWING;
                break;
            case "MATURING":
                lStatus = LandStatus.RIPE;
                break;
            default:
                lStatus = LandStatus.EMPTY;
        }
        return lStatus;
    }

    private List<Plant> getPercentPlants(List<Plant> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        List<Plant> temp = new ArrayList<>();
        double size = Math.ceil(list.size() * ((float) PORCENTAJE / 100));
        for (int i = 0; i < size; i++) {
            temp.add(list.get(rand.nextInt(list.size())));
        }
        return temp;
    }

    private void assignWorkersToLands() {
        initializeMap();
        List<Worker> lWorkers = getPaidWorkers();
        Collections.shuffle(lWorkers); // for the same workers not to assignationMap in the same land
        int[] wpl = calculateMaximumWorkersPerLand(lands);
        int kont = 0;
        for (int i = 0; i < wpl[1]; i++) {
            List<Worker> temp = assignationMap.get(lands.get(i));
            for (int j = kont; j < (kont + wpl[0]); j++) {
                temp.add(lWorkers.get(j));
            }
            kont += wpl[0];
            assignationMap.put(lands.get(i), temp);
        }
        for (int i = wpl[1]; i < lands.size() - wpl[1]; i++) {
            List<Worker> temp = assignationMap.get(lands.get(i));
            for (int j = kont; j < (kont + wpl[0] - 1); j++) {
                temp.add(lWorkers.get(j));
            }
            kont += (wpl[0] - 1);
            assignationMap.put(lands.get(i), temp);
        }
    }

    private void initializeMap() {
        Map<Land, List<Plant>> landPlantList = PlantThread.getLandAndPlantList();
        lands = new ArrayList<>(landPlantList.keySet());
        assignationMap = new HashMap<>();
        for (Land land : lands) {
            assignationMap.put(land, new ArrayList<>());
        }
    }

    private int[] calculateMaximumWorkersPerLand(List<Land> lands) {
        int maxWpl = (int) Math.ceil((float) SimulationProcesses.getFarm().getNumWorkers() / lands.size());
        int numLandsWpl = lands.size() * (SimulationProcesses.getFarm().getNumWorkers() % lands.size());
        return new int[] { maxWpl, (numLandsWpl == 0) ? lands.size() : numLandsWpl };
    }

    private List<Worker> getPaidWorkers() {
        List<Worker> list = new ArrayList<>();
        for (Worker w : workers) {
            if (w.isPagado()) {
                list.add(w);
            }
        }
        return list;
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

    // public static void payWorkers() {

    // }
}
