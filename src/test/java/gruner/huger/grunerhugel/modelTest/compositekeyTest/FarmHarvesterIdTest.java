package gruner.huger.grunerhugel.modelTest.compositekeyTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.compositekey.FarmHarvesterId;

public class FarmHarvesterIdTest {
    /*@Test
   public void coverage(){
        FarmHarvesterId farmHarvesterId =new FarmHarvesterId();
        farmHarvesterId.setFarmId(1);
        FarmHarvesterId a = new FarmHarvesterId();
        FarmHarvesterId b = new FarmHarvesterId();

        farmHarvesterId.hashCode();
        assertEquals(true,farmHarvesterId.equals(farmHarvesterId));
        assertEquals(false, farmHarvesterId.equals(null));
        assertEquals(true, a.equals(b));



        assertEquals(false,farmHarvesterId.equals(new FarmHarvesterId()));
        farmHarvesterId.toString();
    }*/
    @Test
    public void testHashCode() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, "abc");
        FarmHarvesterId id2 = new FarmHarvesterId(1, "abc");
        FarmHarvesterId id3 = new FarmHarvesterId(2, "def");

        Assert.assertEquals(id1.hashCode(), id2.hashCode());
        Assert.assertNotEquals(id1.hashCode(), id3.hashCode());
    }

    @Test
    public void testEquals() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, "abc");
        Assert.assertTrue(id1.equals(id1));
    }
    @Test
    public void testGettersAndSetters() {
        FarmHarvesterId id = new FarmHarvesterId();
        id.setFarmId(1);
        id.setHarvesterId("abc");

        Assert.assertEquals(1, (int) id.getFarmId());
        Assert.assertEquals("abc", id.getHarvesterId());
    }

////////////////////////////////////////////

    @Test
    public void testEqualsWithEqualObjects() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, "abc");
        FarmHarvesterId id2 = new FarmHarvesterId(1, "abc");

        Assert.assertTrue(id1.equals(id2));
    }

    @Test
    public void testEqualsWithDifferentFarmId() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, "abc");
        FarmHarvesterId id2 = new FarmHarvesterId(2, "abc");

        Assert.assertFalse(id1.equals(id2));
    }

    @Test
    public void testEqualsWithDifferentHarvesterId() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, "abc");
        FarmHarvesterId id2 = new FarmHarvesterId(1, "def");

        Assert.assertFalse(id1.equals(id2));
    }

    @Test
    public void testEqualsWithDifferentObjects() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, "abc");
        String str = "not an FarmHarvesterId object";

        Assert.assertFalse(id1.equals(str));
    }

    @Test
    public void testEqualsWithNullObject() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, "abc");

        Assert.assertFalse(id1.equals(null));
    }

    @Test
    public void testHashCodeWithEqualObjects() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, "abc");
        FarmHarvesterId id2 = new FarmHarvesterId(1, "abc");

        Assert.assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentFarmId() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, "abc");
        FarmHarvesterId id2 = new FarmHarvesterId(2, "abc");

        System.out.println(id1.hashCode());
        System.out.println(id2.hashCode());
        Assert.assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentHarvesterId() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, "abc");
        FarmHarvesterId id2 = new FarmHarvesterId(1, "def");

        Assert.assertNotEquals(id1.hashCode(), id2.hashCode());
    }

    
    @Test
    public void testHashCodeWithNullHarvesterId() {
        FarmHarvesterId id1 = new FarmHarvesterId(1, null);
        FarmHarvesterId id2 = new FarmHarvesterId(1, "abc");

        Assert.assertNotEquals(id1.hashCode(), id2.hashCode());
    }



}
