package gruner.huger.grunerhugel.model;

// import java.util.concurrent.BlockingQueue;

import java.util.concurrent.BlockingQueue;

import gruner.huger.grunerhugel.simulation.Message;

public class Worker extends Thread {

    private boolean pagado;
    BlockingQueue<Message> queue;

    public Worker(BlockingQueue<Message> blockingQueue) {
        this.queue = blockingQueue;
    }

    @Override
    public void run() {
        //  no need
    }

    public void work() {
        // no need
    }

    public boolean isPagado(){
        return pagado;
    }

    public void setPagado(boolean value){
        pagado = value;
    }
}
