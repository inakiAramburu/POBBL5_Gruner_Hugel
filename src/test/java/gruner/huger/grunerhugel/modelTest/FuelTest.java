package gruner.huger.grunerhugel.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.Fuel;

@SpringBootTest
public class FuelTest {

    @Test
    public void testGetSet() {
        Fuel fuel = new Fuel();

        // ID
        fuel.setFuelId(null);
        assertEquals(null, fuel.getFuelId());

        // Price
        fuel.setPrice(10);
        assertEquals(10, fuel.getPrice());

        // Currency
        fuel.setCurrency("EURO");
        assertEquals("EURO", fuel.getCurrency());

    }

    @Test
    public void equalsTest() {
        Fuel fuel = new Fuel();
        Fuel fuel2 = new Fuel();
        Fuel fuel3 = new Fuel();
        fuel3.setPrice(15);
        // true
        assertTrue(fuel.equals(fuel2));
        assertTrue(fuel.equals(fuel));

        // false
        assertFalse(fuel.equals(fuel3));
        assertFalse(fuel.equals(null));
    }

    @Test
    public void hashCodeTest() {
        Fuel fuel = new Fuel();
        Fuel fuel2 = new Fuel();

        // true
        assertEquals(fuel.hashCode(), fuel2.hashCode());
        assertEquals(fuel.hashCode(), fuel.hashCode());

    }
}
