package gruner.huger.grunerhugel.simulation.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import gruner.huger.grunerhugel.simulation.Message;
import gruner.huger.grunerhugel.simulation.enumeration.Sign;
import gruner.huger.grunerhugel.GrunerhugelApplication;

public class Balance extends Thread {
    public static final int WORKER_PAYMENT = 800;
    private static int balance;
    static Object mutex;
    BlockingQueue<Message> blockingQueue;
    private static boolean check;
    private static Lock lock;
    private static Condition checking;

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

    private static void moneyCost(int cost) {
        synchronized (mutex) {
            balance -= cost;
        }
    }

    private static void moneyEarned(int earn) {
        synchronized (mutex) {
            balance += earn;
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            awaitCheck();
            readMessages();
        }
    }

    private void awaitCheck() {
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

    private void readMessages() {
        List<Message> list = new ArrayList<>();
        blockingQueue.drainTo(list);
        list.forEach(this::doAction);
    }

    private void doAction(Message msg){
        if(Sign.PLUS.equals(msg.getSign())){
            moneyEarned(msg.getQuantity());
        } else {
            moneyCost(msg.getQuantity());
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
}
