package gruner.huger.grunerhugel.simulation.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Worker;
import gruner.huger.grunerhugel.simulation.Message;
import gruner.huger.grunerhugel.simulation.SimulationProcesses;
import gruner.huger.grunerhugel.simulation.enumeration.Sign;

public class WorkerThread extends Thread {
    private static final String WORKTYPE_UNEMPLOYED = "Unemployed";
    private static final String WORKTYPE_SOWING = "Sowing";
    private static final String WORKTYPE_MAINTAINING = "Maintaining";
    private static final String WORKTYPE_REAPING = "Reaping";
    private Map<Land, List<Worker>> work;
    private List<Land> lands;
    private LandRepository lRepository;
    private static List<Worker> workers;
    private boolean check;
    private boolean pause;
    private static boolean workingHours;
    private Lock mutex;
    private Condition checking;
    private static BlockingQueue<Message> queue;

    public WorkerThread(LandRepository landRepository,
            BlockingQueue<Message> blockingQueue) {
        queue = blockingQueue;
        this.work = new HashMap<>();
        this.pause = false;
        this.mutex = new ReentrantLock();
        this.checking = this.mutex.newCondition();
        this.lRepository = landRepository;
        initializeMapLands();
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            // assign lands
            assignLandsToWorkers();
            // do work
            startWorking();
            // -----------------------------------------------
            while (!pause) {
                awaitCheck();
                if (check && workingHours) {
                    updateLandStatus();
                    assignLandsToWorkers();
                }
            }
            if (pause) {
                // await
                initializeMapLands();
            }
        }
    }

    private void startWorking() {
        for (Land land : lands) {
            // reconocer el tipo del trabajo segun el estado del campo
            String wType = getWorkType(land.getStatus());
            // calcular el tiempo del trabajo segun los recursos disponibles (cantidad de
            // trabajadores, tractores...)
            calculateWorkHours(wType);
            // mandar a los trabajadores del campo el tiempo que tienen que trabajar
            // workers.work();
        }
    }

    private void calculateWorkHours(String wType) {
        //  no need
    }

    private String getWorkType(String status) {
        String temp;
        switch (status) {
            case "Empty":
                temp = WORKTYPE_SOWING;
                break;
            case WORKTYPE_SOWING:
                temp = WORKTYPE_SOWING;
                break;
            case "Growing":
                temp = WORKTYPE_MAINTAINING;
                break;
            case "Matured":
                temp = WORKTYPE_REAPING;
                break;
            default:
                temp = WORKTYPE_MAINTAINING;
        }
        return temp;
    }

    private void updateLandStatus() {
        // no need
    }

    private void assignLandsToWorkers() {
        List<Worker> lWorkers = getPaidWorkers();
        int[] wpl = calculateMaximumWorkersPerLand(lands);
        int kont = 0;
        for (int i = 0; i < wpl[1]; i++) {
            List<Worker> temp = work.get(lands.get(i));
            for (int j = kont; j < (kont + wpl[0]); j++) {
                temp.add(lWorkers.get(j));
            }
            kont += wpl[0];
            work.put(lands.get(i), temp);
        }
        for (int i = wpl[1]; i < lands.size(); i++) {
            List<Worker> temp = work.get(lands.get(i));
            for (int j = kont; j < (kont + wpl[0] - 1); j++) {
                temp.add(lWorkers.get(j));
            }
            kont += (wpl[0] - 1);
            work.put(lands.get(i), temp);
        }
    }

    private int[] calculateMaximumWorkersPerLand(List<Land> lands) {
        int maxWpl = (int) Math.ceil((float) SimulationProcesses.getFarm().getNumWorkers() / lands.size());
        int numLandsWpl = lands.size() * (SimulationProcesses.getFarm().getNumWorkers() % lands.size());
        return new int[] { maxWpl, numLandsWpl };
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

    private void awaitCheck() {
        while (!check) {
            mutex.lock();
            try {
                checking.await();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("WorkerThread Interrupted!");
                this.interrupt();
            } finally {
                mutex.unlock();
            }
        }
    }

    public static void payWorkers() {
        if (!needToBePaid()) {
            try {
                queue.put(new Message(Sign.MINUS, workers.size() * Balance.WORKER_PAYMENT,
                        "Worker payment (x" + workers.size() + ")"));
                workers.forEach(worker -> worker.setPagado(true));
                // workers.forEach(worker -> wRepository.save(worker));
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("Error in worker payment!");
                Thread.currentThread().interrupt();
            }
        }
    }

    private void initializeMapLands() {
        this.lands = (List<Land>) lRepository.findByFarm(SimulationProcesses.getFarm());
        for (Land land : lands) {
            work.putIfAbsent(land, new ArrayList<>());
        }
    }

    private static boolean needToBePaid() {
        boolean resultado = true;
        int i = 0;
        while (resultado && i < workers.size()) {
            resultado = workers.get(i).isPagado();
            i++;
        }
        return resultado;
    }

    public static void setWorkingHours(boolean value) {
        workingHours = value;
    }
}
