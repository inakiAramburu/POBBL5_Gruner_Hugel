package gruner.huger.grunerhugel.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Fuel;

public class FuelTest {
    
    
    @Test
    public void testGetSet(){
        Fuel fuel = new Fuel();
        

        
        //ID
        fuel.setFuelId(null);
        assertEquals(fuel.getFuelId(), null);

        //Price
        fuel.setPrice(10);
        assertEquals(fuel.getPrice(), 10);
        
        //Currency
        fuel.setCurrency("EURO");
        assertEquals(fuel.getCurrency(), "EURO");

        
    }

    @Test
    public void equalsTest(){
        Fuel fuel = new Fuel();
        Fuel fuel2 = new Fuel();
        Fuel fuel3 = new Fuel();
        fuel3.setPrice(15);
        //true
        assertEquals(fuel.equals(fuel2), true);
        assertEquals(fuel.equals(fuel), true);

        //false
        assertEquals(fuel.equals(fuel3), false);
        assertEquals(fuel.equals(null), false);
    }
    
    @Test
    public void hashCodeTest(){
        Fuel fuel = new Fuel();
        Fuel fuel2 = new Fuel();

        //true
        assertEquals(fuel.hashCode(), fuel2.hashCode());
        assertEquals(fuel.hashCode(), fuel.hashCode());


    }
}
