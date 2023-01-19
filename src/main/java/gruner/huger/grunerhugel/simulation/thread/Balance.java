package gruner.huger.grunerhugel.simulation.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

import gruner.huger.grunerhugel.simulation.Message;
import gruner.huger.grunerhugel.simulation.enumeration.Sign;
import gruner.huger.grunerhugel.GrunerhugelApplication;

//import java.util.concurrent.BlockingQueue;
// import java.util.logging.Level;

// import gruner.huger.grunerhugel.GrunerhugelApplication;
// import gruner.huger.grunerhugel.simulation.Simulation;

public class Balance extends Thread { // implements Runnable
    public static final int WORKER_PAYMENT = 800;
    private static int balance;
    static Object mutex;
    BlockingQueue<Message> blockingQueue;
    private static boolean check;
    private static Lock lock;
    private static Condition checking;

    public Balance(int initBalance, BlockingQueue<Message> blockingQueue) {
        mutex = new Object();
        this.blockingQueue = blockingQueue;
        setBalance(initBalance);
        lock = new ReentrantLock();
        checking = lock.newCondition();
    }

    public int getBalance() {
        synchronized (mutex) {
            return balance;
        }
    }

    private static void setBalance(int moneyAdded) { // to avoid a code smell
        synchronized (mutex) {
            balance = moneyAdded;
        }
    }

    private static void moneyCost(int cost) {
        synchronized (mutex) {
            GrunerhugelApplication.logger.log(Level.INFO, "======\nBalance: -{0}", cost);
            balance -= cost;
            GrunerhugelApplication.logger.log(Level.INFO, "Balance: {0}", balance);
        }
    }

    private static void moneyEarned(int earn) {
        synchronized (mutex) {
            GrunerhugelApplication.logger.log(Level.INFO, "======\nBalance: -{0}", earn);
            balance += earn;
            GrunerhugelApplication.logger.log(Level.INFO, "Balance: {0}", balance);
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
        blockingQueue.forEach(this::doAction);
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
