package gruner.huger.grunerhugel.simulation.thread;

import java.util.Date;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.SimulationRepository;
import gruner.huger.grunerhugel.model.Simulation;
import gruner.huger.grunerhugel.simulation.SimulationProcesses;

public class TimeThread extends Thread {
    private static final int HOUR_DURATION = 1250; // miliseconds
    private SimulationRepository simRepository;
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
    private static int accelerator = 1;
    private static boolean pause = false;
    private static Date actualDate;
    private Date endDate;

    public TimeThread(Date startDate, Date endDate, SimulationRepository simulationRepository) {
        // no need
        this.simRepository = simulationRepository;
        actualDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void run() {
        try {
            while (!pause && checkDate()) {
                WeatherThread.callSignal();
                isFirstHourOfMonth();
                isWorkingHours();
                isDayEnding();
                Thread.sleep(HOUR_DURATION / accelerator);
                updateActualDate();
            }
            saveActualDate();
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("TimeThread was interrupted!");
            this.interrupt();
        }
        GrunerhugelApplication.logger.info("Time PAUSE");
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
        try {
            PlantThread.pause();
            Balance.pause();
            FuelThread.pause();
            WheatPriceThread.pause();
            LandThread.pause();
            WeatherThread.pause();
            Thread.sleep(500);
            SimulationProcesses.pause();
            pause = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void setAccelerator(int acceleration) {
        accelerator = acceleration;
    }

    public static int getAccelerator() {
        return accelerator;
    }

    public static Date getActualDate() {
        return actualDate;
    }

    public static void isWorkingHours() {
        int hours = getHours();
        LandThread.workingHours(WORKING_HOURS_MIN <= hours && WORKING_HOURS_MAX > hours);
    }

    public static void isDayEnding() {
        if (getHours() == DAY_ENDING_HOUR) {
            Balance.callSignal();
        }
    }

    private void isFirstHourOfMonth() {
        if (getHours() == DAY_STARTING_HOUR && getDay() == MONTH_STARTING_DAY) {
            WheatPriceThread.callSignal();
            FuelThread.callSignal();
            LandThread.payWorkers();
        }
    }

    private static int getDay() {
        String date = actualDate.toString();
        return Integer.parseInt(date.split(" ")[0].split("-")[2]);
    }

    private static int getHours() {
        String time = actualDate.toString();
        return Integer.parseInt(time.split(" ")[1].split(":")[0]);
    }

    private void saveActualDate() {
        Simulation sim = simRepository.findByFarm(SimulationProcesses.getFarm());
        sim.setStartDate(actualDate);
        simRepository.save(sim);
    }
}
