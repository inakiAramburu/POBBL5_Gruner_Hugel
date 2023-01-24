package gruner.huger.grunerhugel.simulation.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.simulation.Message;
import gruner.huger.grunerhugel.simulation.enumeration.PlantType;
import gruner.huger.grunerhugel.simulation.enumeration.Sign;

public class Worker extends Thread {
    private static final int KG_PER_SEED_SACK = 30;
    public static final String COMMAND_SEEDS = "BUY AND PLANT SEEDS";
    public static final String COMMAND_FUEL = "BUY FUEL";
    public static final String COMMAND_SELL = "SELL PRODUCT";
    public static final String COMMAND_MANTAIN = "MANTAIN LANDS";
    public static final String COMMAND_HARVEST = "HARVEST PRODUCTS";
    private static int workingHours = 0;
    private boolean paid;
    private static boolean pause = false;
    private BlockingQueue<Message> queue;
    private Semaphore payCheck;
    private Semaphore toWork;
    private Semaphore work;
    private String command;
    private double temp;
    private Object mutex = new Object();

    public Worker(BlockingQueue<Message> blockingQueue) {
        this.queue = blockingQueue;
        this.paid = false;
        this.payCheck = new Semaphore(0);
        this.toWork = new Semaphore(0);
        this.work = new Semaphore(0);
    }

    @Override
    public void run() {
        try {
            while (!pause) {
                payCheck.acquire();
                awaitWork();
            }
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("Worker " + getId() + " Interrupted!");
            this.interrupt();
        }
    }

    private void awaitWork() {
        try {
            while (paid) {
                toWork.acquire();
                doWork();
                work.acquire();
            }
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("Working Interrupted!");
            this.interrupt();
        }
    }

    private void doWork() {
        switch (command) {
            case COMMAND_FUEL:
                buyFuel((int)temp);
                break;
            case COMMAND_SEEDS:
                buySeeds(temp, PlantType.WHEAT);
                break;
            case COMMAND_SELL:
                sellProducts(temp, PlantType.WHEAT);
                break;
            case COMMAND_HARVEST, COMMAND_MANTAIN:
            default:
        }
    }

    private void buyFuel(int litres) {
        buy(FuelThread.buyFuel(litres), "Cost of fuel for vehicles");
    }

    private void buySeeds(double size, PlantType pType) {
        double numSeed = ((pType.getKgsPerHa() * size) / KG_PER_SEED_SACK);
        buy(numSeed * pType.getSeedPrice(),
                "Cost of seeds of " + pType.getPlantType() + " for " + size + " hectares.");
        LandThread.addNewPlant(this, pType, numSeed);
    }

    private void buy(double cost, String concept) {
        try {
            queue.put(new Message(Sign.MINUS, cost, concept));
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("Buying Interrupted!");
            this.interrupt();
        }
    }

    private void sellProducts(double quantity, PlantType pType) {
        try {
            Message msg = new Message(Sign.PLUS, WheatPriceThread.sellWheat(quantity),
                    "Sell of " + quantity + " tons of " + pType.getPlantType());
            queue.put(msg);
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("Selling Interrupted!");
            this.interrupt();
        }
    }

    // -----------------------------------------------------

    public void setWork(int workHours, String instruction, Double variable) {
        synchronized (mutex) {
            workingHours = workHours;
            command = instruction;
            temp = variable;
        }
        toWork.release();
    }

    public void pay() {
        paid = true;
        payCheck.release();
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPagado(boolean value) {
        paid = value;
    }

    public long getCount() {
        synchronized (mutex) {
            return workingHours;
        }
    }

    public void hourPass() {
        synchronized (mutex) {
            if (workingHours > 0) {
                GrunerhugelApplication.logger.info("Worker countedDown");
                workingHours--;
            }
            if (workingHours == 0) {
                work.release();
            }
        }
    }

    public void unpay() {
        paid = false;
    }

    public void pause(){
        pause = true;
        this.interrupt();
    }
}
