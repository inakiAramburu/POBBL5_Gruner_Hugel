package gruner.huger.grunerhugel.model;

import java.util.logging.Level;

import gruner.huger.grunerhugel.GrunerhugelApplication;

public class Balance {  //implements Runnable
    private static int totalMoney;
    static Object mutex = new Object();

    public Balance(int initBalance) {
        setBalance(initBalance);
    }

    public int getBalance() {
        synchronized (mutex) {
            return totalMoney;
        }
    }

    private static void setBalance(int moneyAdded) {
        synchronized (mutex) {
            totalMoney = moneyAdded;
        }
    }

    public static void moneyCost(int cost) {
        synchronized (mutex) {
            GrunerhugelApplication.logger.log(Level.INFO, "======\nBalance: -{0}.", cost);
            totalMoney -= cost;
            GrunerhugelApplication.logger.log(Level.INFO, "Balance: {0}.", totalMoney);
        }
    }

    public static void moneyEarned(int earn) {
        synchronized (mutex) {
            GrunerhugelApplication.logger.log(Level.INFO, "======\nBalance: +{0}.", earn);
            totalMoney += earn;
            GrunerhugelApplication.logger.log(Level.INFO, "Balance: {0}.", totalMoney);
        }
    }
}
