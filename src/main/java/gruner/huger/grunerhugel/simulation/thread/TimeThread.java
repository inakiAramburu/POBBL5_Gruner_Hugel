package gruner.huger.grunerhugel.simulation.thread;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;

import gruner.huger.grunerhugel.GrunerhugelApplication;

public class TimeThread extends Thread {
    private static final int HOUR_DURATION = 1250; // miliseconds
    // private static final int HOURS_DAY = 24; // hours
    public static final String TIME_PAUSE = "PAUSE";
    public static final String TIME_RESUME = "RESUME";
    public static final String HOUR_PASS = "HOUR PASS";
    public static final String TERMINATE = "TERMINATE";
    private static final int WORKING_HOURS_MIN = 6;
    private static final int WORKING_HOURS_MAX = 15;
    private static final int MONTH_STARTING_DAY = 1;
    private static final int DAY_STARTING_HOUR = 0;
    private static final int DAY_ENDING_HOUR = 23;
    // private final int MILISECONDS_PER_SECOND = 1000;
    // private final int SECONDS_PER_MINUTE = 60;
    // private final int MINUTES_PER_HOUR = 60;
    // private final int HOURS_PER_DAY = 24;
    public static CountDownLatch cDownLatch;
    private static PropertyChangeSupport pcs;
    private int accelerator = 4;
    private static boolean pause = false;
    private static Date actualDate;
    private Date endDate;

    public TimeThread(Date startDate, Date endDate) {
        // no need
        pcs = new PropertyChangeSupport(this);
        actualDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void run() {
        GrunerhugelApplication.logger.log(Level.INFO, "TimeThread Id: {0}", this.getId());
        try {
            while (!Thread.interrupted()) {
                while (!pause && checkDate()) {
                    Thread.sleep(HOUR_DURATION / accelerator);
                    GrunerhugelApplication.logger.log(Level.INFO, "Date: {0}", DateFormat.getDateTimeInstance().format(actualDate));
                    // WeatherThread.callSignal();
                    isFirstHourOfMonth();
                    isWorkingHours();
                    isDayEnding();
                    updateActualDate();
                }
                if (!pause) {
                    GrunerhugelApplication.logger.info(TERMINATE);
                    notifyListeners(TERMINATE, null);
                } else {
                    GrunerhugelApplication.logger.info(TIME_PAUSE);
                    cDownLatch.await();
                }
            }
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("TimeThread was interrupted!");
            this.interrupt();
        }
    }

    private boolean checkDate() {
        return actualDate.before(endDate);
    }

    private void updateActualDate() { // adds an hour to the actual date
        actualDate.setTime(actualDate.getTime() + getHoursInMs(1));
    }

    private int getHoursInMs(int hours) {
        return hours * 60 * 60 * 1000; // minutes seconds miliseconds
    }

    public static void pause() {
        cDownLatch = new CountDownLatch(1);
        pause = true;
        notifyListeners(TIME_PAUSE, cDownLatch);
    }

    public static void play() {
        cDownLatch.countDown();
        pause = false;
        notifyListeners(TIME_RESUME, null);
    }

    private static void notifyListeners(String codeName, Object newValue) {
        pcs.firePropertyChange(codeName, null, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void setAccelerator(int accelerator) {
        this.accelerator = accelerator;
    }

    public int getAccelerator() {
        return accelerator;
    }

    public static Date getActualDate() {
        return actualDate;
    }

    public static void isWorkingHours() {
        int hours = getHours();
        boolean workingHours = false;
        if (WORKING_HOURS_MIN <= hours && WORKING_HOURS_MAX > hours) {
            workingHours = true;
        }
        WorkerThread.setWorkingHours(workingHours);
    }

    public static void isDayEnding() {
        if (getHours() == DAY_ENDING_HOUR) {
            Balance.callSignal();
        }
    }

    private void isFirstHourOfMonth() {
        if(getHours()==DAY_STARTING_HOUR && getDay()==MONTH_STARTING_DAY){
            // WorkerThread.payWorkers();
            WheatPriceThread.callSignal();
        }
    }

    private static int getDay(){
        String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(actualDate);
        return Integer.parseInt(date.split(" ")[0]);
    }

    private static int getHours() {
        String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(actualDate);
        return Integer.parseInt(time.split(":")[0]);
    }
}
