package gruner.huger.grunerhugel.modelTest.compositekeyTest;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.compositekey.FarmPlowId;

public class FarmPlowIdTest {
    // Prueba del constructor
    @Test
    public void testConstructor() {
        FarmPlowId id = new FarmPlowId(1, "plow1");
        assertEquals(1, id.getFarmId().intValue());
        assertEquals("plow1", id.getPlowId());
    }
    
    // Prueba de los getters y setters
    @Test
    public void testGettersAndSetters() {
        FarmPlowId id = new FarmPlowId();
        id.setFarmId(2);
        id.setPlowId("plow2");
        assertEquals(2, id.getFarmId().intValue());
        assertEquals("plow2", id.getPlowId());
    }
    
    // Prueba del método hashCode
    @Test
    public void testHashCode() {
        FarmPlowId id1 = new FarmPlowId(1, "plow1");
        FarmPlowId id2 = new FarmPlowId(1, "plow1");
        assertEquals(id1.hashCode(), id2.hashCode());
    }
    
    // Prueba del método equals
    @Test
    public void testEquals() {
        FarmPlowId id1 = new FarmPlowId(1, "plow1");
        FarmPlowId id2 = new FarmPlowId(1, "plow1");
        assertTrue(id1.equals(id2));
    }
    // Prueba del método equals con objetos de diferentes clases
    @Test
    public void testEqualsWithDifferentObjectClass() {
        FarmPlowId id1 = new FarmPlowId(1, "plow1");
        String id2 = "not an FarmPlowId object";
        assertNotEquals(id1, id2);
    }

    public void testEquals_whenFarmIdNotEqual() {
        FarmPlowId id1 = new FarmPlowId(1, "plow1");
        FarmPlowId id2 = new FarmPlowId(2, "plow1");
        assertFalse(id1.equals(id2));
    }

    @Test
    public void testEquals_whenPlowIdNotEqual() {
        FarmPlowId id1 = new FarmPlowId(1, "plow1");
        FarmPlowId id2 = new FarmPlowId(1, "plow2");
        assertFalse(id1.equals(id2));
    }

    @Test
    public void testEquals_whenThisObjectIsEqualToOtherObject() {
        FarmPlowId id1 = new FarmPlowId(1, "plow1");
        assertTrue(id1.equals(id1));
    }

    @Test
    public void testEquals_whenOtherObjectIsNull() {
        FarmPlowId id1 = new FarmPlowId(1, "plow1");
        FarmPlowId id2 = null;
        assertFalse(id1.equals(id2));
    }
}