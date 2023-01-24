package gruner.huger.grunerhugel.simulation.thread;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.FarmTractorRepository;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.model.FarmTractor;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Tractor;
import gruner.huger.grunerhugel.simulation.Message;
import gruner.huger.grunerhugel.simulation.SimulationProcesses;
import gruner.huger.grunerhugel.simulation.enumeration.LandStatus;
import gruner.huger.grunerhugel.simulation.enumeration.PlantType;

public class LandThread extends Thread {
    private static final int PORCENTAJE = 65;
    private static Map<Land, List<Worker>> assignationMap;
    private static List<Land> lands = new ArrayList<>();
    private static LandRepository lRepository;
    private static List<Worker> workers;
    private static boolean check = false;
    private static boolean pause = false;
    private static boolean workingHours = false;
    private static Lock mutex = new ReentrantLock();
    private static Condition checking = mutex.newCondition();
    private static BlockingQueue<Message> queue;
    private Random rand;
    private List<Tractor> tList;
    private Map<Land, Tractor> tMap;
    private static Semaphore payment = new Semaphore(0);

    public LandThread(LandRepository landRepository, BlockingQueue<Message> blockingQueue,
            FarmTractorRepository fTractRepository) {
        queue = blockingQueue;
        workers = setWorkers();
        lRepository = landRepository;
        assignationMap = new HashMap<>();
        this.rand = new SecureRandom();
        getListTractor(fTractRepository);
        this.tMap = new HashMap<>();
        payWorkers();
    }

    private List<Worker> setWorkers() {
        List<Worker> temp = new ArrayList<>();
        for (int i = 0; i < SimulationProcesses.getFarm().getNumWorkers(); i++) {
            Worker worker = new Worker(queue);
            temp.add(worker);
            worker.start();
        }
        return temp;
    }

    private void getListTractor(FarmTractorRepository fTractRepository) {
        List<FarmTractor> fTractor = fTractRepository.findByFarm(SimulationProcesses.getFarm());
        tList = new ArrayList<>();
        fTractor.forEach(t -> tList.add(t.getTractor()));
    }

    @Override
    public void run() {
        assignWorkersToLands();
        while (!pause) {
            awaitPayment();
            awaitCheck();
            updateLandStatus();
            work();
            check = false;
        }
        GrunerhugelApplication.logger.info("Land PAUSE");
    }

    private void awaitPayment() {
        if (getPaidWorkers().isEmpty()) {
            try {
                payment.acquire();
                assignWorkersToLands();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("Payment wait Interrupted!");
                this.interrupt();
            }
        }
    }

    private void assignWorkersToLands() {
        initializeMap();
        List<Worker> lWorkers = getPaidWorkers();
        if (!lWorkers.isEmpty()) {
            Collections.shuffle(lWorkers); // for the same workers not to work in the same land
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
    }

    private void initializeMap() {
        Map<Land, List<Plant>> landPlantList = PlantThread.getLandAndPlantList();
        lands = new ArrayList<>(landPlantList.keySet());
        assignationMap = new HashMap<>();
        for (Land land : lands) {
            assignationMap.put(land, new ArrayList<>());
        }
    }

    private List<Worker> getPaidWorkers() {
        List<Worker> list = new ArrayList<>();
        for (Worker w : workers) {
            if (w.isPaid()) {
                list.add(w);
            }
        }
        return list;
    }

    private int[] calculateMaximumWorkersPerLand(List<Land> lands) {
        int maxWpl = (int) Math.ceil((float) SimulationProcesses.getFarm().getNumWorkers() / lands.size());
        int numLandsWpl = lands.size() * (SimulationProcesses.getFarm().getNumWorkers() % lands.size());
        return new int[] { maxWpl, (numLandsWpl == 0) ? lands.size() : numLandsWpl };
    }

    private void awaitCheck() {
        while (!workingHours || !check) {
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
        for (Entry<Land, List<Plant>> entry : landPlantList.entrySet()) {
            List<Plant> plants = getPercentPlants(entry.getValue());
            String status = getPlantsStatus(plants);
            entry.getKey().setStatus(status);
        }
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

    private LandStatus getLandStatusFromPlant(String status) {
        LandStatus lStatus;
        switch (status) {
            case "GERMINATION", "VEGETATIVE", "TILLERING", "ANTHESIS", "MILKY", "PASTY", "MATURATION":
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

    private void work() {
        for (Entry<Land, List<Worker>> lEntry : assignationMap.entrySet()) {
            if (!lEntry.getValue().isEmpty() && lEntry.getValue().get(0).getCount() == 0) {
                deassignTractorFromLand(lEntry.getKey());
                assignTractorToLand(lEntry.getKey());
                int workHours = calculateWorkHours(lEntry.getKey().getSize(),
                        getLandStatus(lEntry.getKey().getStatus()), lEntry.getValue().size(),
                        calculateTractorEfficiency(lEntry.getKey()));
                initializeWorkers(lEntry.getValue(), workHours, lEntry.getKey());
            }
        }
    }

    private void assignTractorToLand(Land land) {
        if (!tList.isEmpty()) {
            Tractor tractor = tList.get(0);
            tList.remove(tractor);
            tMap.put(land, tractor);
        }
    }

    private void deassignTractorFromLand(Land land) {
        Tractor tractor = tMap.get(land);
        if (tractor != null) {
            tList.add(tractor);
            tMap.remove(land);
            tMap.put(land, null);
        }
    }

    private int calculateWorkHours(double size, LandStatus status, int numWorkers, double tractorEfficiency) {
        double totalCost = size * status.getCost();
        if (numWorkers > 1) {
            totalCost -= numWorkers * 0.45;
        }
        totalCost *= (tractorEfficiency / 100);
        return (int) Math.ceil(totalCost);
    }

    private LandStatus getLandStatus(String status) {
        LandStatus lStatus;
        if (status.equals(LandStatus.EMPTY.getStatus())) {
            lStatus = LandStatus.EMPTY;
        } else if (status.equals(LandStatus.PLANTING.getStatus())) {
            lStatus = LandStatus.PLANTING;
        }else if(status.equals(LandStatus.RIPE.getStatus())){
            lStatus = LandStatus.RIPE;
        } else if (status.equals(LandStatus.HARVESTING.getStatus())) {
            lStatus = LandStatus.HARVESTING;
        } else {
            lStatus = LandStatus.GROWING;
        }
        return lStatus;
    }

    private double calculateTractorEfficiency(Land land) {
        Tractor tractor = tMap.get(land);
        double efficiency = 1;
        if (tractor != null) {
            efficiency = (double) tractor.getFuelCapacity() / tractor.getMaxSpeed();
            efficiency = tractor.getPower() / efficiency;
            efficiency /= 100;
        }
        return efficiency;
    }

    private void initializeWorkers(List<Worker> value, int workHours, Land land) {
        for (Worker w : value) {
            String[] command = getCommandAndVariable(land.getStatus(), land);
            w.setWork(workHours, command[0], Double.valueOf(command[1]));
        }
    }

    private String[] getCommandAndVariable(String status, Land land) {
        String command;
        String number;
        switch (status) {
            case "Ripe":
                command = Worker.COMMAND_HARVEST;
                number = String.valueOf(land.getSize() * PlantType.WHEAT.getTPerHa());
                break;
            case "Empty":
                command = Worker.COMMAND_SEEDS;
                number = String.valueOf(land.getSize());
                break;
            case "Growing":
            default:
                command = Worker.COMMAND_MANTAIN;
                number = "0";
        }
        return new String[] {command,number};
    }

    public void saveLand() {
        lRepository.saveAll(assignationMap.keySet());
    }

    // -----------------------------------

    public static void addNewPlant(Worker worker, PlantType pType, double numSeed) {
        Land land = getLandFromWorker(worker);
        if (land != null) {
            PlantThread.addPlant(land, pType, (int)numSeed);
        }
    }

    public static Land getLandFromWorker(Worker worker) {
        Land land = null;
        for (Entry<Land, List<Worker>> entry : assignationMap.entrySet()) {
            if (entry.getValue().contains(worker)) {
                land = entry.getKey();
                break;
            }
        }
        return land;
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

    public static void payWorkers() {
        int paidWorkers = Balance.payWorkers(workers.size());
        for (int i = 0; i < paidWorkers; i++) {
            workers.get(i).pay();
        }
        for (int i = paidWorkers; i < workers.size(); i++) {
            workers.get(i).unpay();
        }
        payment.release();
    }

    public static void pause() {
        List<Land> old = lRepository.findByFarm(SimulationProcesses.getFarm());
        for(int i=0;i<old.size();i++){
            for(int j=0;j<lands.size();j++){
                if(old.get(i).getId() == lands.get(j).getId()){
                    old.get(i).setStatus(lands.get(j).getStatus());
                }
            }
        }
        lRepository.saveAll(old);
        pause = true;
    }

    public static void workingHours(boolean value) {
        workingHours = value;
    }

    public static List<Land> getLands(){
        return new ArrayList<>(assignationMap.keySet());
    }
}
