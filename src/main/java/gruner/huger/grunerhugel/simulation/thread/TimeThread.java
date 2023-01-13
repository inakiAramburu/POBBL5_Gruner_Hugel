package gruner.huger.grunerhugel.simulation.thread;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

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
    private static CountDownLatch cLatch;
    private static PropertyChangeSupport pcs;
    private int accelerator = 1;
    private static boolean pause = false;
    private Date actualDate;
    private Date endDate;

    public TimeThread(Date startDate, Date endDate){
        //  no need
        pcs = new PropertyChangeSupport(this);
        this.actualDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                while(!pause && checkDate()){    //  checkDate here or in simulation (can be notified to listeners)
                    Thread.sleep(HOUR_DURATION);
                    updateActualDate();
                    notifyListeners(HOUR_PASS, actualDate);
                }
                if(!pause){
                    pcs.firePropertyChange(TERMINATE, null, null);
                        //intrupt(?)
                } else {
                    cLatch.await();
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
        cLatch = new CountDownLatch(1);
        pause = true;
        notifyListeners(TIME_PAUSE, cLatch);
    }

    public static void play(){
        cLatch.countDown();
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

    public Date getActualDate(){
        return actualDate;
    }
}
