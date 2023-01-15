package gruner.huger.grunerhugel.simulation.thread;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.logging.Level;

import gruner.huger.grunerhugel.GrunerhugelApplication;

public class TimeThread extends Thread {
    private static final int HOUR_DURATION = 1250; // miliseconds
    // private static final int HOURS_DAY = 24; // hours
    public static final String TIME_PAUSE = "PAUSE";
    public static final String TIME_RESUME = "RESUME";
    public static final String HOUR_PASS = "HOUR PASS";
    public static final String TERMINATE = "TERMINATE";
    // private final int MILISECONDS_PER_SECOND = 1000;
    // private final int SECONDS_PER_MINUTE = 60;
    // private final int MINUTES_PER_HOUR = 60;
    // private final int HOURS_PER_DAY = 24;
    public static CountDownLatch cDownLatch;
    private static PropertyChangeSupport pcs;
    private int accelerator = 1;
    private static boolean pause = false;
    private static Date actualDate;
    private Date endDate;
    private Condition cond;

    public TimeThread(Date startDate, Date endDate){
        //  no need
        pcs = new PropertyChangeSupport(this);
        this.actualDate = startDate;
        this.endDate = endDate;
    }

    public void setCondition(Condition condition){
        this.cond = condition;
    }

    @Override
    public void run() {
        GrunerhugelApplication.logger.log(Level.INFO, "TimeThread Id: {0}",this.getId());
        try {
            while(!Thread.interrupted()){
                while(!pause && checkDate()){    //  checkDate here or in simulation (can be notified to listeners)
                    Thread.sleep(HOUR_DURATION / accelerator);
                    updateActualDate();
                    WeatherThread.callSignal();
                    // notifyListeners(HOUR_PASS, actualDate);
                    GrunerhugelApplication.logger.info("HOUR_PASS");
                }
                if(!pause){
                    GrunerhugelApplication.logger.info("TERMINATE");
                    notifyListeners(TERMINATE, null);
                } else {
                    GrunerhugelApplication.logger.info("PAUSE");
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

    private void updateActualDate() {   //  adds an hour to the actual date
        actualDate.setTime(actualDate.getTime() + getHoursInMs(1));
    }

    private int getHoursInMs(int hours) {
        return hours * 60 * 60 * 1000;  //  minutes seconds miliseconds
    }

    public static void pause(){
        cDownLatch = new CountDownLatch(1);
        pause = true;
        notifyListeners(TIME_PAUSE, null);
    }

    public static void play(){
        cDownLatch.countDown();
        pause = false;
        notifyListeners(TIME_RESUME, null);
    }
    
    private static void notifyListeners(String codeName, Object newValue) {
        pcs.firePropertyChange(codeName, null, newValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }

    public void setAccelerator(int accelerator){
        this.accelerator = accelerator;
    }

    public int getAccelerator(){
        return accelerator;
    }

    public static Date getActualDate(){
        return actualDate;
    }
}
