package gruner.huger.grunerhugel.model;

public class Balance {
    private static int balance;
    static Object mutex; // object to restraint the entrance to this class' functions

    public Balance(int initBalance) {
        setBalance(initBalance);
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

    public static void moneyCost(int cost) {
        synchronized (mutex) {
            balance -= cost;
        }
    }

    public static void moneyEarned(int earn) {
        synchronized (mutex) {
            balance += earn;
        }
    }
}
