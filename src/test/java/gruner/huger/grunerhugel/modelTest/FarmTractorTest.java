package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;


import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmTractor;
import gruner.huger.grunerhugel.model.Tractor;
import gruner.huger.grunerhugel.model.compositekey.FarmTractorId;
import jakarta.persistence.EmbeddedId;

public class FarmTractorTest {
    
    @EmbeddedId
    private FarmTractorId id;


    @Test
    public void costructorTest(){
        Farm farm = new Farm();
        Tractor tractor = new Tractor();
        FarmTractor farmTractor = new FarmTractor(farm, tractor, 10);
        assertEquals(farmTractor.getFarm(), farm);
        assertEquals(farmTractor.getTractor(), tractor);
        assertEquals(farmTractor.getQuantity(), 10);
    }
    @Test
    public void testGetSet(){
        Farm farm = new Farm();
        Tractor tractor = new Tractor();
        FarmTractor farmTractor = new FarmTractor();
        farmTractor.setFarm(farm);
        farmTractor.setTractor(tractor);
        farmTractor.setQuantity(10);
        farmTractor.setId(id);
        assertEquals(farmTractor.getId(), id);
        assertEquals(farmTractor.getFarm(), farm);
        assertEquals(farmTractor.getTractor(), tractor);
        assertEquals(farmTractor.getQuantity(), 10);

        //toString
        assertEquals(farmTractor.toString(), "FarmTractor [farm=" + farm + ", tractor=" + tractor + ", quantity=" + 10 + "]");
    }
}