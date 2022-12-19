package gruner.huger.grunerhugel.simulation;

// import java.util.concurrent.BlockingQueue;
import gruner.huger.grunerhugel.model.Balance;
import gruner.huger.grunerhugel.model.Worker;

public class Simulation implements Runnable {
    static final int HOUR_DURATION = 1250; // miliseconds
    static final int HOURS_DAY = 24; // hours
    Thread time;
    private static boolean horaConcluida = false;
    int horas = 0;
    int accelerator = 1;
    Balance balance;
    // BlockingQueue<String> queue;

    public Simulation(int initialBalance) {
        this.time = new Thread(this);
        balance = new Balance(initialBalance); // queue
    }

    public void start() {
        time.start();
    }

    @Override
    public void run() {
        try {
            while (horas != HOURS_DAY) {
                Thread.sleep(HOUR_DURATION / accelerator);
                // GrunerhugelApplication.logger.log(Level.INFO,"Hora: {0}.", horas);
                horas++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getHoras() {
        return horas;
    }

    public int getAccelerator() {
        return accelerator;
    }

    public void setAccelerator(int accelerator) {
        this.accelerator = accelerator;
    }
}
