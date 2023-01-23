package gruner.huger.grunerhugel.modelTest.compositekeyTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.Year;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.compositekey.FuelId;

@SpringBootTest
public class FuelIdTest {
    // Prueba del constructor
    @Test
    public void testConstructor() {
        FuelId id = new FuelId(Year.of(2020), 1);
        assertEquals(Year.of(2020), id.getYear());
        assertEquals(1, id.getWeek());
    }

    // Prueba de los getters y setters
    @Test
    public void testGettersAndSetters() {
        FuelId id = new FuelId();
        id.setYear(Year.of(2021));
        id.setWeek(2);
        assertEquals(Year.of(2021), id.getYear());
        assertEquals(2, id.getWeek());
    }

    // Prueba del método hashCode
    @Test
    public void testHashCode() {
        FuelId id1 = new FuelId(Year.of(2020), 1);
        FuelId id2 = new FuelId(Year.of(2020), 1);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    // Prueba del método equals
    @Test
    public void testEquals() {
        FuelId id1 = new FuelId(Year.of(2020), 1);
        FuelId id2 = new FuelId(Year.of(2020), 1);
        assertTrue(id1.equals(id2));
    }

    // Prueba del método equals cuando los años son diferentes
    @Test
    public void testEquals_whenYearNotEqual() {
        FuelId id1 = new FuelId(Year.of(2020), 1);
        FuelId id2 = new FuelId(Year.of(2021), 1);
        assertFalse(id1.equals(id2));
    }

    // Prueba del método equals cuando las semanas son diferentes
    @Test
    public void testEquals_whenWeekNotEqual() {
        FuelId id1 = new FuelId(Year.of(2020), 1);
        FuelId id2 = new FuelId(Year.of(2020), 2);
        assertFalse(id1.equals(id2));
    }

    // Prueba del método equals con objetos de diferentes clases
    @Test
    public void testEqualsWithDifferentObjectClass() {
        FuelId id1 = new FuelId(Year.of(2020), 1);
        String id2 = "not an FuelId object";
        assertNotEquals(id1, id2);
    }

    // Prueba del método equals cuando el objeto actual es igual al objeto comparado
    @Test
    public void testEquals_whenThisObjectIsEqualToOtherObject() {
        FuelId id1 = new FuelId(Year.of(2020), 1);
        assertTrue(id1.equals(id1));
    }

    // Prueba del método equals cuando el objeto comparado es null
    @Test
    public void testEquals_whenOtherObjectIsNull() {
        FuelId id1 = new FuelId(Year.of(2020), 1);
        FuelId id2 = null;
        assertFalse(id1.equals(id2));
    }

    // Prueba del método toString()
    @Test
    public void testToString() {
        FuelId id1 = new FuelId(Year.of(2020), 1);
        assertEquals("FuelIdentity [year=2020, week=1]", id1.toString());
    }

    @Test
    public void testEquals_nullYear_returnFalse() {
        FuelId fuelId = new FuelId();
        FuelId fuelId2 = new FuelId();
        fuelId2.setYear(Year.of(2000));
        assertFalse(fuelId.equals(fuelId2));
    }

    @Test
    public void testhashCode_nullYear_returnZero() {
        FuelId fuelId = new FuelId();
        fuelId.setYear(null);
        assertEquals(961, fuelId.hashCode());
    }

}
