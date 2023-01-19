package gruner.huger.grunerhugel.model;

// import java.util.concurrent.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.simulation.Message;

public class Worker extends Thread {

    private boolean pagado;
    private boolean jobFinished;
    private BlockingQueue<Message> queue;
    private static CountDownLatch cLatch;

    public Worker(BlockingQueue<Message> blockingQueue) {
        this.queue = blockingQueue;
        this.pagado = true;
        this.jobFinished = false;
    }

    @Override
    public void run() {
        try {
            cLatch.await();
            jobFinished = true;
        } catch (InterruptedException e) {
            GrunerhugelApplication.logger.warning("Worker " + getId() + " Interrupted!");
            this.interrupt();
        }
    }

    public void setWork(int workHours) {
        jobFinished = false;
        cLatch = new CountDownLatch(workHours);
    }

    public void sell(int quantity) {
        // no need
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean value) {
        pagado = value;
    }

    public long getCount() {
        long result;
        if (cLatch == null) {
            result = -1;
        } else if (jobFinished) {
            result = 0;
        } else {
            result = cLatch.getCount();
        }
        return result;
    }

    public void hourPass() {
        if (cLatch != null && cLatch.getCount()!=0){
            GrunerhugelApplication.logger.info("Worker countedDown");
            cLatch.countDown();
        }
    }
}
