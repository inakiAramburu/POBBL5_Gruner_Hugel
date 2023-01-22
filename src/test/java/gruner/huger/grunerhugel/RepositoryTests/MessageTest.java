package gruner.huger.grunerhugel.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.simulation.Message;
import gruner.huger.grunerhugel.simulation.enumeration.Sign;

public class MessageTest {
    
    @Test
    void createMessage() {
        Sign sign = Sign.PLUS;
        Message ms= new Message(sign, 5,"test");
        //assertEquals(ms, ms); 
}

}