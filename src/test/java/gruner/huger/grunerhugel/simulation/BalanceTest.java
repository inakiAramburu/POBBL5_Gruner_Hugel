package gruner.huger.grunerhugel.simulation;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.simulation.thread.Balance;

@SpringBootTest
class BalanceTest {

    @Test
    void testcontructor() {
        double initialBalance = 100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();
        new Balance(initialBalance, blockingQueue);
        assertEquals(Balance.getBalance(), initialBalance, 0.0);
    }

    @Test
    void testrun() {
        double initialBalance = 100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();
        new Balance(initialBalance, blockingQueue);
        Balance.pause();
        assertEquals(Balance.getBalance(), initialBalance, 0.0);
    }

    @Test
    void testgetBalance() {
        double initialBalance = 100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();
        new Balance(initialBalance, blockingQueue);
        assertEquals(Balance.getBalance(), initialBalance, 0.0);
    }

    @Test
    void testsetTotalMoney() {
        double initialBalance = 100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();
        new Balance(initialBalance, blockingQueue);
        Balance.setTotalMoney(10);
        assertEquals(Balance.getBalance(), 10, 0.0);
    }

    @Test
    void testGetLMessage() {
        double initialBalance = 100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();
        new Balance(initialBalance, blockingQueue);
        assertEquals(Balance.getlMessages().size(), 0);
    }

    @Test
    void testMonetCost() {
        double initialBalance = 100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();
        new Balance(initialBalance, blockingQueue);
        Balance.moneyCost(10);
        assertEquals(Balance.getBalance(), 90);
        Balance.moneyEarned(10.0);
        assertEquals(100, Balance.getBalance());
    }

    @Test
    void testcoverage() {
        double initialBalance = 100;
        BlockingQueue<Message> blockingQueue = new LinkedBlockingQueue<>();
        Balance balance = new Balance(initialBalance, blockingQueue);
        Message ms = new Message(null, initialBalance, null);
        Balance.callSignal();
        Balance.payWorkers(1);
        balance.doAction(ms);
        balance.readMessages();
        balance.awaitCheck();

    }

}
