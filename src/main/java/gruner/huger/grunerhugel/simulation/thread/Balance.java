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
    public static int balance;
    public static Object mutex;
    private BlockingQueue<Message> blockingQueue;
    private static boolean check;
    private static Lock lock;
    private static Condition checking;
    private static boolean pause = false;

    public Balance(double initialBalance, BlockingQueue<Message> blockingQueue) {
        mutex = new Object();
        this.blockingQueue = blockingQueue;
        balance = (int) initialBalance;
        lock = new ReentrantLock();
        checking = lock.newCondition();
    }

    public int getBalance() {
        synchronized (mutex) {
            return balance;
        }
    }

    public static void moneyCost(double cost) {
        synchronized (mutex) {
            balance -= cost;
        }
    }

    public static void moneyEarned(double earn) {
        synchronized (mutex) {
            balance += earn;
        }
    }

    @Override
    public void run() {
        while (!pause) {
            awaitCheck();
            readMessages();
        }
        saveBalance();
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
        blockingQueue.drainTo(list);
        list.forEach(this::doAction);
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
            while (balance > 0 && numWorkers > count) {
                balance -= WORKER_PAYMENT;
                count++;
            }
            if (balance <= 0) {
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

    public static void pause(){
        pause = true;
    }

    public void saveBalance() {
        synchronized (mutex) {
            SimulationProcesses.setMoney(balance);
        }
    }
}
