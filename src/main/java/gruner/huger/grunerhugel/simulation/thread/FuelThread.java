package gruner.huger.grunerhugel.simulation.thread;

import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.FuelRepository;
import gruner.huger.grunerhugel.model.Fuel;

public class FuelThread extends Thread {
    private static Fuel fuel;
    private FuelRepository fRepository;
    private static boolean check = false;
    private static Lock mutex;
    private static Condition checking;

    public FuelThread(FuelRepository fuelRepository) {
        this.fRepository = fuelRepository;
        mutex = new ReentrantLock();
        checking = mutex.newCondition();
    }

    @Override
    public void run() {
        GrunerhugelApplication.logger.log(Level.INFO, "TimeThread Id: {0}", this.getId());
        while (!Thread.interrupted()) {
            if (check) {
                GrunerhugelApplication.logger.info("UPDATE FUEL");
                updateFuel();
                check = false;
            }
            awaitCheck();
        }
    }

    private void updateFuel() {
        Date date = TimeThread.getActualDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Year year = Year.of(Integer.parseInt(DateFormat.getDateInstance(DateFormat.MEDIUM).format(date).split(" ")[2]));
        int week = 0;
        if (year.isAfter(Year.of(2001))) {
            week = cal.get(Calendar.WEEK_OF_YEAR);
        }
        Optional<Fuel> opFuel = fRepository.findByYearAndPeriod(year, week);
        if(opFuel.isPresent()){
            fuel = opFuel.get();
        }
    }

    private void awaitCheck() {
        while (!check) {
            mutex.lock();
            try {
                checking.await();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("FuelThread Interrupted!");
                this.interrupt();
            } finally {
                mutex.unlock();
            }
        }
    }

    public static void callSignal() {
        mutex.lock();
        try {
            check = true;
            checking.signal();
        } finally {
            mutex.unlock();
        }
    }

    public static double buyFuel(int litres) {
        return litres * fuel.getPrice();
    }
}