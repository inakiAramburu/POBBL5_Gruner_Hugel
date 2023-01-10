package gruner.huger.grunerhugel.model;

//import java.util.concurrent.BlockingQueue;
// import java.util.logging.Level;

// import gruner.huger.grunerhugel.GrunerhugelApplication;
// import gruner.huger.grunerhugel.simulation.Simulation;

public class Balance {  //implements Runnable
    private static int balance;
    static Object mutex = new Object(); // object to restraint the entrance to this class' functions
    // BlockingQueue<String> blockingQueue;

    public Balance(int initBalance) { //BlockingQueue<String> blockingQueue
        setBalance(initBalance);
        // this.blockingQueue = blockingQueue;
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
            System.out.println("======\nBalance: -"+cost);
            balance -= cost;
            System.out.println("Balance: "+balance);
        }
    }

    public static void moneyEarned(int earn) {
        synchronized (mutex) {
            System.out.println("======\nBalance: +"+earn);
            balance += earn;
            System.out.println("Balance: "+balance);
        }
    }

    // @Override
    // public void run() {
    //     while (Simulation.getHoraConcluida()) {
    //         try {
    //             String mensaje = blockingQueue.take();
    //             descifrarMensaje(mensaje);
    //         } catch (InterruptedException e) {
    //             GrunerhugelApplication.logger.log(Level.WARNING, "Interrupted!", e);
    //             
    //             Thread.currentThread().interrupt();
    //         }
    //     }
    // }

    // private void descifrarMensaje(String mensaje) {
    //     String[] mensajes = mensaje.split(" ");
    //     if(mensajes[0].equals("+")){
    //         moneyEarned(Integer.parseInt(mensajes[1]));
    //     } else {
    //         moneyCost(Integer.parseInt(mensajes[1]));
    //     }
    // }
}
