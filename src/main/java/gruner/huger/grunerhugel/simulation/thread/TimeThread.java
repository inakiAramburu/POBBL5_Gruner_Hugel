package gruner.huger.grunerhugel.simulation.thread;

import java.text.DateFormat;
import java.util.Date;
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
    private static final int MILISECONDS_PER_SECOND = 1000;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int HOURS_TO_UPDATE = 1;
    private int accelerator = 1;
    private static boolean pause = false;
    private static Date actualDate;
    private Date endDate;

    public TimeThread(Date startDate, Date endDate) {
        // no need
        actualDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void run() {
        GrunerhugelApplication.logger.log(Level.INFO, "TimeThread Id: {0}", this.getId());
        try {
            while (!pause && checkDate()) {
                GrunerhugelApplication.logger.log(Level.INFO, "Date: {0}",
                DateFormat.getDateTimeInstance().format(actualDate));
                WeatherThread.callSignal();
                isFirstHourOfMonth();
                isWorkingHours();
                isDayEnding();
                Thread.sleep(HOUR_DURATION / accelerator);
                updateActualDate();
            }
            GrunerhugelApplication.logger.info(TERMINATE);
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("TimeThread was interrupted!");
            this.interrupt();
        }
    }

    private boolean checkDate() {
        return actualDate.before(endDate);
    }

    private void updateActualDate() { // adds an hour to the actual date
        actualDate.setTime(actualDate.getTime() + getHoursInMs(HOURS_TO_UPDATE));
    }

    private int getHoursInMs(int hours) {
        return hours * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * MILISECONDS_PER_SECOND;
    }

    public static void pause() {
        pause = true;
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
        if (WORKING_HOURS_MIN <= hours && WORKING_HOURS_MAX > hours) {
            LandThread.callSignal();
        }
    }

    public static void isDayEnding() {
        if (getHours() == DAY_ENDING_HOUR) {
            Balance.callSignal();
        }
    }

    private void isFirstHourOfMonth() {
        if (getHours() == DAY_STARTING_HOUR && getDay() == MONTH_STARTING_DAY) {
            // WorkerThread.payWorkers();
            WheatPriceThread.callSignal();
            FuelThread.callSignal();
        }
    }

    private static int getDay() {
        String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(actualDate);
        return Integer.parseInt(date.split(" ")[0]);
    }

    private static int getHours() {
        String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(actualDate);
        return Integer.parseInt(time.split(":")[0]);
    }
}
