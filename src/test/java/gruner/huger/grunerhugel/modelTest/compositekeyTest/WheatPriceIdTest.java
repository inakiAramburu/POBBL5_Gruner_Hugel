package gruner.huger.grunerhugel.modelTest.compositekeyTest;


import static org.junit.Assert.*;
import java.time.Year;
import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.compositekey.WheatPriceId;

public class WheatPriceIdTest {

    @Test
    public void testGettersAndSetters() {
        WheatPriceId id = new WheatPriceId();
        id.setMonth("January");
        id.setYear(Year.of(2022));
        assertEquals("January", id.getMonth());
        assertEquals(Year.of(2022), id.getYear());
    }

    @Test
    public void testEquals() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id3 = new WheatPriceId(Year.of(2021), "January");
        WheatPriceId id4 = new WheatPriceId(Year.of(2022), "February");
        assertTrue(id1.equals(id2));
        assertFalse(id1.equals(id3));
        assertFalse(id1.equals(id4));
    }

    @Test
    public void testHashCode() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id3 = new WheatPriceId(Year.of(2021), "January");
        WheatPriceId id4 = new WheatPriceId(Year.of(2022), "February");
        assertEquals(id1.hashCode(), id2.hashCode());
        assertNotEquals(id1.hashCode(), id3.hashCode());
        assertNotEquals(id1.hashCode(), id4.hashCode());
    }


    @Test
    public void testEqualsWithEqualObjects() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), "January");
        assertTrue(id1.equals(id2));
    }

    @Test
    public void testEqualsWithDifferentObjects() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2021), "January");
        assertFalse(id1.equals(id2));
    }

    @Test
    public void testEqualsWithDifferentYear() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2021), "January");
        assertFalse(id1.equals(id2));
    }

    @Test
    public void testEqualsWithDifferentMonth() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), "February");
        assertFalse(id1.equals(id2));
    }

    @Test
    public void testEqualsWithNullObject() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        assertFalse(id1.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClassObject() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        String str = "some string";
        assertFalse(id1.equals(str));
    }

    @Test
    public void testEqualsWithNullYear() {
        WheatPriceId id1 = new WheatPriceId(null, "January");
        WheatPriceId id2 = new WheatPriceId(null, "January");
        assertTrue(id1.equals(id2));
    }

    @Test
    public void testEqualsWithNullMonth() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), null);
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), null);
        assertTrue(id1.equals(id2));
    }

    @Test
    public void testEqualsWithOtherMonthNotNull() {
    WheatPriceId id1 = new WheatPriceId(Year.of(2022), null);
    WheatPriceId id2 = new WheatPriceId(Year.of(2022), "January");
    assertFalse(id1.equals(id2)); 
    }
    @Test
    public void testEqualsWithSameObject() {
    WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
    WheatPriceId id2 = id1;
    assertTrue(id1.equals(id2)); 
    }


    @Test
    public void testToString() {
        WheatPriceId id = new WheatPriceId(Year.of(2022), "January");
        assertEquals("WheatPriceId [month=January, year=2022]", id.toString());
    }

    @Test
    public void testHashCode2() {
    WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
    WheatPriceId id2 = new WheatPriceId(Year.of(2022), "January");
    WheatPriceId id3 = new WheatPriceId(Year.of(2021), "January");
    WheatPriceId id4 = new WheatPriceId(Year.of(2022), "February");
    WheatPriceId id5 = new WheatPriceId(null, "February");

    assertEquals(id1.hashCode(), id2.hashCode());
    assertNotEquals(id1.hashCode(), id3.hashCode());
    assertNotEquals(id1.hashCode(), id4.hashCode());
    assertNotEquals(id1.hashCode(), id5.hashCode());
}


}
