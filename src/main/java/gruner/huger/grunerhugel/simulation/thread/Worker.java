package gruner.huger.grunerhugel.simulation.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
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
    private boolean paid;
    private boolean jobFinished = true;
    private boolean pause = false;
    private BlockingQueue<Message> queue;
    private static CountDownLatch cLatch;
    private Semaphore payCheck;
    private Semaphore work;
    private String command;
    private Integer temp;

    public Worker(BlockingQueue<Message> blockingQueue) {
        this.queue = blockingQueue;
        this.paid = false;
        this.payCheck = new Semaphore(0);
        this.work = new Semaphore(0);
        cLatch = new CountDownLatch(0);
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
                work.acquire();
                doWork();
                cLatch.await();
                jobFinished = true;
            }
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("Working Interrupted!");
            this.interrupt();
        }
    }

    private void doWork() {
        switch (command) {
            case COMMAND_FUEL:
                buyFuel(temp);
                break;
            case COMMAND_SEEDS:
                buySeeds(temp, PlantType.WHEAT);
                break;
            case COMMAND_SELL:
                sellProducts(temp, PlantType.WHEAT);
                break;
            case COMMAND_HARVEST:
            case COMMAND_MANTAIN:
            default:
        }
        jobFinished = false;
    }

    private void buyFuel(int litres) {
        buy(FuelThread.buyFuel(litres), "Cost of fuel for vehicles");
    }

    private void buySeeds(double size, PlantType pType) {
        buy(((pType.getKgsPerHa() * size) / KG_PER_SEED_SACK) * pType.getSeedPrice(),
                "Cost of seeds of " + pType.getPlantType() + " for " + size + " hectares.");
    }

    private void buy(double cost, String concept) {
        try {
            queue.put(new Message(Sign.MINUS, cost, concept));
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("Buying Interrupted!");
            this.interrupt();
        }
    }

    private void sellProducts(int quantity, PlantType pType) {
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

    public void setWork(int workHours, String instruction, Integer variable) {
        cLatch = new CountDownLatch(workHours);
        command = instruction;
        temp = variable;
        work.release();
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
        long result;
        if (cLatch == null) {
            result = -2;
        } else if (jobFinished) {
            result = -1;
        } else {
            result = cLatch.getCount();
        }
        return result;
    }

    public void hourPass() {
        if (cLatch != null && cLatch.getCount() != 0) {
            GrunerhugelApplication.logger.info("Worker countedDown");
            cLatch.countDown();
        }
    }

    public void unpay() {
        paid = false;
    }
}
