package gruner.huger.grunerhugel.simulation;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Simulation implements Runnable {
    static final int HOUR_DURATION = 1250; // miliseconds
    static final int HOURS_DAY = 24; // hours
    Thread time;
    boolean horaConcluida;
    int balance;
    int horas = 0;
    int accelerator = 1;
    Logger logger;

    public Simulation() {
        this.time = new Thread(this);
        this.horaConcluida = false;
        logger = Logger.getLogger(Simulation.class.getName());
    }

    public void start() {
        time.start();
    }

    @Override
    public void run() {
        while (horas != HOURS_DAY) {
            try {
                Thread.sleep(HOUR_DURATION / accelerator);
                logger.log(Level.INFO,"Hora: {0}.", horas);
                horas++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
