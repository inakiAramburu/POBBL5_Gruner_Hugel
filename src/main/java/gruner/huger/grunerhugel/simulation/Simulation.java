package gruner.huger.grunerhugel.simulation;

public class Simulation implements Runnable {
    static final int HOUR_DURATION = 1250; // miliseconds
    static final int HOURS_DAY = 24; // hours
    Thread time;
    boolean horaConcluida;
    int balance;
    int horas = 0;
    int accelerator = 1;


    public Simulation() {
        this.time = new Thread(this);
        this.horaConcluida = false;
    }

    public void start() {
        time.start();
    }

    @Override
    public void run() {
        while (horas != HOURS_DAY) {
            try {
                time.sleep(HOUR_DURATION / accelerator);
                System.out.println("Hora: " + horas++);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.interrupted();
            }
        }
    }

}
