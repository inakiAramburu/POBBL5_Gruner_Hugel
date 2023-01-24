package gruner.huger.grunerhugel.modelTest.compositekeyTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.Year;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.compositekey.WheatPriceId;

@SpringBootTest
class WheatPriceIdTest {

    @Test
    void testGettersAndSetters() {
        WheatPriceId id = new WheatPriceId();
        id.setMonth("January");
        id.setYear(Year.of(2022));
        assertEquals("January", id.getMonth());
        assertEquals(Year.of(2022), id.getYear());
    }

    @Test
    void testEquals() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id3 = new WheatPriceId(Year.of(2021), "January");
        WheatPriceId id4 = new WheatPriceId(Year.of(2022), "February");
        assertEquals(true, id1.equals(id2));
        assertEquals(false, id1.equals(id3));
        assertEquals(false, id1.equals(id4));
    }

    @Test
    void testHashCode() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id3 = new WheatPriceId(Year.of(2021), "January");
        WheatPriceId id4 = new WheatPriceId(Year.of(2022), "February");
        assertEquals(id1.hashCode(), id2.hashCode());
        assertNotEquals(id1.hashCode(), id3.hashCode());
        assertNotEquals(id1.hashCode(), id4.hashCode());
    }

    @Test
    void testEqualsWithEqualObjects() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), "January");
        assertEquals(true, id1.equals(id2));
    }

    @Test
    void testEqualsWithDifferentObjects() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2021), "January");
        assertEquals(false, id1.equals(id2));
    }

    @Test
    void testEqualsWithDifferentYear() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2021), "January");
        assertEquals(false, id1.equals(id2));
    }

    @Test
    void testEqualsWithDifferentMonth() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), "February");
        assertEquals(false, id1.equals(id2));
    }

    @Test
    void testEqualsWithNullObject() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        assertEquals(false, id1.equals(null));
    }

    @Test
    void testEqualsWithDifferentClassObject() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        String str = "some string";
        assertEquals(false, id1.equals(str));
    }

    @Test
    void testEqualsWithNullYear() {
        WheatPriceId id1 = new WheatPriceId(null, "January");
        WheatPriceId id2 = new WheatPriceId(null, "January");
        assertEquals(true, id1.equals(id2));
    }

    @Test
    void testEqualsWithNullMonth() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), null);
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), null);
        assertEquals(true, id1.equals(id2));
    }

    @Test
    void testEqualsWithOtherMonthNotNull() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), null);
        WheatPriceId id2 = new WheatPriceId(Year.of(2022), "January");
        assertEquals(false, id1.equals(id2));
    }

    @Test
    void testEqualsWithSameObject() {
        WheatPriceId id1 = new WheatPriceId(Year.of(2022), "January");
        WheatPriceId id2 = id1;
        assertEquals(true, id1.equals(id2));
    }

    @Test
    void testToString() {
        WheatPriceId id = new WheatPriceId(Year.of(2022), "January");
        assertEquals("WheatPriceId [month=January, year=2022]", id.toString());
    }

    @Test
    void testHashCode2() {
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
