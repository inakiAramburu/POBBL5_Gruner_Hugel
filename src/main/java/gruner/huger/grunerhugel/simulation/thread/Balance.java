package gruner.huger.grunerhugel.simulation.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.simulation.Message;
import gruner.huger.grunerhugel.simulation.SimulationProcesses;
import gruner.huger.grunerhugel.simulation.enumeration.Sign;

public class Balance extends Thread {
    public static final int WORKER_PAYMENT = 800;
    private static int totalMoney;
    private static Object mutex = new Object();
    private BlockingQueue<Message> blockingQueue;
    private static boolean check;
    private static Lock lock = new ReentrantLock();
    private static Condition checking = lock.newCondition();
    private static boolean pause = false;
    protected static List<Message> lMessages = new ArrayList<>();

    public Balance(double initialBalance, BlockingQueue<Message> blockingQueue) {
        this.blockingQueue = blockingQueue;
        Balance.setTotalMoney((int) initialBalance);
    }

    public static void setTotalMoney(int totalMoney) {
        Balance.totalMoney = totalMoney;
    }

    public static int getBalance() {
        synchronized (mutex) {
            return totalMoney;
        }
    }

    public static void moneyCost(double cost) {
        synchronized (mutex) {
            totalMoney -= cost;
        }
    }

    public static void moneyEarned(double earn) {
        synchronized (mutex) {
            totalMoney += earn;
        }
    }

    @Override
    public void run() {
        while (!pause) {
            awaitCheck();
            readMessages();
        }
        saveBalance();
        GrunerhugelApplication.logger.info("Balance PAUSE");
    }

    public void awaitCheck() {
        while (!check) {
            lock.lock();
            try {
                checking.await();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("PlantThread Interrupted!");
                this.interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void readMessages() {
        List<Message> list = new ArrayList<>();
        if (!blockingQueue.isEmpty()) {
            blockingQueue.drainTo(list);
            list.forEach(this::doAction);
            lMessages.addAll(list);
        }
    }

    public void doAction(Message msg) {
        if (Sign.PLUS.equals(msg.getSign())) {
            moneyEarned(msg.getQuantity());
        } else {
            moneyCost(msg.getQuantity());
        }
    }

    public static int payWorkers(int numWorkers) {
        synchronized (mutex) {
            int count = 0;
            int result;
            while (totalMoney > 0 && numWorkers > count) {
                totalMoney -= WORKER_PAYMENT;
                count++;
            }
            if (totalMoney <= 0) {
                result = -1;
            } else {
                result = count;
            }
            return result;
        }
    }

    public static void callSignal() {
        lock.lock();
        try {
            check = true;
            checking.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void pause() {
        callSignal();
        pause = true;
    }

    public void saveBalance() {
        synchronized (mutex) {
            SimulationProcesses.setMoney(totalMoney);
        }
    }

    public static List<Message> getlMessages() {
        return lMessages;
    }

}
