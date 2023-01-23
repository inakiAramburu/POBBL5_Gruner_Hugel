package gruner.huger.grunerhugel.modelTest.compositekeyTest;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.compositekey.FarmPlowId;

@SpringBootTest
public class FarmPlowIdTest {
    // Prueba del constructor
    // Pruebas para el constructor
    @Test
    public void testConstructor() {
        FarmPlowId id = new FarmPlowId(1, "plow1");
        assertEquals(1, id.getFarmId().intValue());
        assertEquals("plow1", id.getPlowId());
    }

    // Pruebas para los getters y setters
    @Test
    public void testGetFarmId() {
        FarmPlowId id = new FarmPlowId();
        id.setFarmId(2);
        assertEquals(2, id.getFarmId().intValue());
    }

    @Test
    public void testGetPlowId() {
        FarmPlowId id = new FarmPlowId();
        id.setPlowId("plow2");
        assertEquals("plow2", id.getPlowId());
    }

    @Test
    public void testSetFarmId() {
        FarmPlowId id = new FarmPlowId();
        id.setFarmId(2);
        assertEquals(2, id.getFarmId().intValue());
    }

    @Test
    public void testSetPlowId() {
        FarmPlowId id = new FarmPlowId();
        id.setPlowId("plow2");
        assertEquals("plow2", id.getPlowId());
    }

    // Pruebas para hashCode
    @Test
    public void testHashCode() {
        FarmPlowId id1 = new FarmPlowId(1, "plow1");
        FarmPlowId id2 = new FarmPlowId(1, "plow1");
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    // Pruebas para equals
    @Test
    public void testEquals() {
        FarmPlowId id1 = new FarmPlowId(1, "plow1");
        FarmPlowId id2 = new FarmPlowId(1, "plow1");
        assertTrue(id1.equals(id2));
    }

    @Test
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

    @Test
    public void testEqualsWithDifferentObjectClass() {
        FarmPlowId id1 = new FarmPlowId(1, "plow1");
        String id2 = "not an FarmPlowId object";
        assertNotEquals(id1, id2);
    }
}
