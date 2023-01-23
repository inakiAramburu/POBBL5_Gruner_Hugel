package gruner.huger.grunerhugel.simulation.thread;

import java.time.Year;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.WheatPriceRepository;
import gruner.huger.grunerhugel.model.WheatPrice;

public class WheatPriceThread extends Thread {
    private static WheatPrice wheatPrice;
    private WheatPriceRepository wpRepository;
    private static boolean check = false;
    private static boolean pause = false;
    private static Lock mutex;
    private static Condition checking;

    public WheatPriceThread(WheatPriceRepository wheatPriceRepository) {
        this.wpRepository = wheatPriceRepository;
        mutex = new ReentrantLock();
        checking = mutex.newCondition();
    }

    @Override
    public void run() {
        while (!pause) {
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
        String[] monthYear = date.toString().split(" ")[0].split("-");
        Year year = Year.of(Integer.parseInt(monthYear[0]));
        String month = "";
        if (year.isAfter(Year.of(1998))) {
            month = monthYear[1];
        }
        Optional<WheatPrice> opWheatPrices = wpRepository.findByYearAndMonth(year, month);
        if (opWheatPrices.isPresent()) {
            wheatPrice = opWheatPrices.get();
        }
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

    public static double sellWheat(int tones) {
        return tones * wheatPrice.getPrice();
    }

    public static void pause() {
        pause = true;
    }
}
