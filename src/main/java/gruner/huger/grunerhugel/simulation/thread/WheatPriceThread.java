package gruner.huger.grunerhugel.simulation.thread;

import java.sql.Time;
import java.text.DateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.FuelRepository;
import gruner.huger.grunerhugel.domain.repository.WheatPriceRepository;
import gruner.huger.grunerhugel.model.Fuel;
import gruner.huger.grunerhugel.model.WheatPrice;

public class WheatPriceThread extends Thread {
    private static WheatPrice wheatPrice;
    private WheatPriceRepository wpRepository;
    private static boolean check = false;
    private static Lock mutex;
    private static Condition checking;

    public WheatPriceThread(WheatPriceRepository wheatPriceRepository) {
        this.wpRepository = wheatPriceRepository;
        mutex = new ReentrantLock();
        checking = mutex.newCondition();
    }

    @Override
    public void run() {
        GrunerhugelApplication.logger.log(Level.INFO, "WheatPriceThread Id: {0}", this.getId());
        while (!Thread.interrupted()) {
            if (check) {
                GrunerhugelApplication.logger.info("UPDATE WHEATPRICE");
                updateWheatPrice();
                check = false;
            }
            awaitCheck();
        }
    }

    private void updateWheatPrice() {
        Date date = TimeThread.getActualDate();
        String[] temp = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date).split(" ");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Year year = Year.of(Integer.parseInt(temp[2]));
        String month = "";
        if (year.isAfter(Year.of(1998))) {
            month = temp[1];
        }
        Iterable<WheatPrice> wheatPrices = wpRepository.findByYearAndMonth(year, month);
    }

    private void awaitCheck() {
        while (!check) {
            mutex.lock();
            try {
                checking.await();
            } catch (InterruptedException e) {
                GrunerhugelApplication.logger.warning("WheatPriceThread Interrupted!");
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

    public static double buyWheatPrice(int tones) {
        return tones * wheatPrice.getPrice();
    }
}
