package gruner.huger.grunerhugel.simulation;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.controller.LoginController;
import gruner.huger.grunerhugel.model.User;
import gruner.huger.grunerhugel.simulation.thread.Balance;

import org.springframework.ui.Model;

public class BalanceTest {
    
    @Test
    public void testcontructor() {
        double initialBalance =100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>() ;
        Balance balance =new Balance(initialBalance, blockingQueue) ;
        assertEquals(balance.getBalance(), initialBalance, 0.0);
    }
    @Test
    public void testrun() {
        double initialBalance =100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>() ;
        Balance balance =new Balance(initialBalance, blockingQueue) ;
        balance.pause();
        assertEquals(balance.getBalance(), initialBalance, 0.0);
    }
    @Test
    public void testgetBalance() {
        double initialBalance =100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>() ;
        Balance balance =new Balance(initialBalance, blockingQueue) ;
        assertEquals(balance.getBalance(), initialBalance, 0.0);
    }
    @Test
    public void testMonetCost(){
        double initialBalance =100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>() ;
        Balance balance =new Balance(initialBalance, blockingQueue) ;
        balance.moneyCost(10);
        assertEquals(balance.getBalance(), 90);
        balance.moneyEarned(10.0);
        assertEquals(100,balance.getBalance());
    }
    @Test
    public void testcoverage(){
        double initialBalance =100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>() ;
        Balance balance =new Balance(initialBalance, blockingQueue) ;
        Message ms=new Message(null, initialBalance, null);
        balance.callSignal();
        balance.payWorkers(1);
        balance.doAction(ms);
        balance.readMessages();
        balance.awaitCheck();

    }
    
}
