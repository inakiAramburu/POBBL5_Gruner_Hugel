package gruner.huger.grunerhugel.simulation;

import java.util.logging.Level;

import groovyjarjarantlr4.v4.codegen.model.Loop;
import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.model.Balance;

public class Simulation implements Runnable {
    static final int HOUR_DURATION = 1250; // miliseconds
    static final int HOURS_DAY = 24; // hours
    Thread time;
    // boolean horaConcluida;
    int horas = 0;
    int accelerator = 1;
    Balance balance;

    public Simulation() {
        this.time = new Thread(this);
        // this.horaConcluida = false;
    }

    public void start() {
        time.start();
    }

    @Override
    public void run() {
        while (horas != HOURS_DAY) {
            try {
                Thread.sleep(HOUR_DURATION / accelerator);
                // GrunerhugelApplication.logger.log(Level.INFO,"Hora: {0}.", horas);
                horas++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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
