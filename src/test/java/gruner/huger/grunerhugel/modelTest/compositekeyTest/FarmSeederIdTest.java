package gruner.huger.grunerhugel.modelTest.compositekeyTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.compositekey.FarmSeederId;

@SpringBootTest
class FarmSeederIdTest {
    // Prueba del constructor
    @Test
    void testConstructor() {
        FarmSeederId id = new FarmSeederId(1, "seeder1");
        assertEquals(1, id.getFarmId().intValue());
        assertEquals("seeder1", id.getSeederId());
    }

    // Prueba de los getters y setters
    @Test
    void testGetFarmId() {
        FarmSeederId id = new FarmSeederId();
        id.setFarmId(2);
        assertEquals(2, id.getFarmId().intValue());
    }

    @Test
    void testGetSeederId() {
        FarmSeederId id = new FarmSeederId();
        id.setSeederId("seeder2");
        assertEquals("seeder2", id.getSeederId());
    }

    @Test
    void testSetFarmId() {
        FarmSeederId id = new FarmSeederId();
        id.setFarmId(2);
        assertEquals(2, id.getFarmId().intValue());
    }

    @Test
    void testSetSeederId() {
        FarmSeederId id = new FarmSeederId();
        id.setSeederId("seeder2");
        assertEquals("seeder2", id.getSeederId());
    }

    // Prueba del método hashCode
    @Test
    void testhashCode() {
        FarmSeederId id1 = new FarmSeederId(1, "seeder1");
        FarmSeederId id2 = new FarmSeederId(1, "seeder1");
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    // Prueba del método equals
    @Test
    void testEquals() {
        FarmSeederId id1 = new FarmSeederId(1, "seeder1");
        FarmSeederId id2 = new FarmSeederId(1, "seeder1");
        assertTrue(id1.equals(id2));
    }

    // Prueba del método equals con objetos de diferentes clases
    @Test
    void testEqualsWithDifferentObjectClass() {
        FarmSeederId id1 = new FarmSeederId(1, "seeder1");
        String id2 = "not an FarmSeederId object";
        assertNotEquals(id1, id2);
    }

    // Prueba del método equals cuando el farmId es diferente
    @Test
    void testEquals_whenFarmIdNotEqual() {
        FarmSeederId id1 = new FarmSeederId(1, "seeder1");
        FarmSeederId id2 = new FarmSeederId(2, "seeder1");
        assertFalse(id1.equals(id2));
    }

    // Prueba del método equals cuando el seederId es diferente
    @Test
    void testEquals_whenSeederIdNotEqual() {
        FarmSeederId id1 = new FarmSeederId(1, "seeder1");
        FarmSeederId id2 = new FarmSeederId(1, "seeder2");
        assertFalse(id1.equals(id2));
    }

    // Prueba del método equals cuando el objeto es igual a sí mismo
    @Test
    void testEquals_whenThisObjectIsEqualToOtherObject() {
        FarmSeederId id1 = new FarmSeederId(1, "seeder1");
        assertTrue(id1.equals(id1));
    }

    // Prueba del método equals cuando el otro objeto es null
    @Test
    void testEquals_whenOtherObjectIsNull() {
        FarmSeederId id1 = new FarmSeederId(1, "seeder1");
        FarmSeederId id2 = null;
        assertFalse(id1.equals(id2));
    }
}
