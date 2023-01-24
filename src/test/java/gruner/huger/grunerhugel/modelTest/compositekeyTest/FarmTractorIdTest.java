package gruner.huger.grunerhugel.modelTest.compositekeyTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.compositekey.FarmTractorId;

@SpringBootTest
class FarmTractorIdTest {
    // Prueba del constructor
    @Test
    void testConstructor() {
        FarmTractorId id = new FarmTractorId(1, "tractor1");
        assertEquals(1, id.getFarmId().intValue());
        assertEquals("tractor1", id.getTractorId());
    }

    // Prueba de los getters y setters
    @Test
    void testGetFarmId() {
        FarmTractorId id = new FarmTractorId();
        id.setFarmId(2);
        assertEquals(2, id.getFarmId().intValue());
    }

    @Test
    void testGetTractorId() {
        FarmTractorId id = new FarmTractorId();
        id.setTractorId("tractor2");
        assertEquals("tractor2", id.getTractorId());
    }

    // Prueba del método hashCode
    @Test
    void testHashCode() {
        FarmTractorId id1 = new FarmTractorId(1, "tractor1");
        FarmTractorId id2 = new FarmTractorId(1, "tractor1");
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    // Prueba del método equals
    @Test
    void testEquals() {
        FarmTractorId id1 = new FarmTractorId(1, "tractor1");
        FarmTractorId id2 = new FarmTractorId(1, "tractor1");
        assertEquals(true, id1.equals(id2));
    }

    // Prueba del método equals con objetos de diferentes clases
    @Test
    void testEqualsWithDifferentObjectClass() {
        FarmTractorId id1 = new FarmTractorId(1, "tractor1");
        String id2 = "not an FarmTractorId object";
        assertNotEquals(id1, id2);
    }

    // Prueba del método equals cuando el farmId es diferente
    @Test
    void testEquals_whenFarmIdNotEqual() {
        FarmTractorId id1 = new FarmTractorId(1, "tractor1");
        FarmTractorId id2 = new FarmTractorId(2, "tractor1");
        assertEquals(false, id1.equals(id2));
    }

    // Prueba del método equals cuando el tractorId es diferente
    @Test
    void testEquals_whenTractorIdNotEqual() {
        FarmTractorId id1 = new FarmTractorId(1, "tractor1");
        FarmTractorId id2 = new FarmTractorId(1, "tractor2");
        assertEquals(false, id1.equals(id2));
    }

    // Prueba del método equals cuando el objeto actual es igual al objeto comparado
    @Test
    void testEquals_whenThisObjectIsEqualToOtherObject() {
        FarmTractorId id1 = new FarmTractorId(1, "tractor1");
        assertEquals(true, id1.equals(id1));
    }

    // Prueba del método equals cuando el objeto comparado es null
    @Test
    void testEquals_whenOtherObjectIsNull() {
        FarmTractorId id1 = new FarmTractorId(1, "tractor1");
        FarmTractorId id2 = null;
        assertEquals(false, id1.equals(id2));
    }

}
