package gruner.huger.grunerhugel.modelTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.Harvester;
import gruner.huger.grunerhugel.model.User;
import gruner.huger.grunerhugel.model.compositekey.FarmHarvesterId;

public class FarmHarvesterTest {

    @Test
    public void testCostructor() {
        FarmHarvester farmHarvester = new FarmHarvester(new Farm(new User(), 1, 0), new Harvester(), 10);
    }


/**
 * 
 */
@Test
    public void testGetsSets() {
        FarmHarvester farmHarvester = new FarmHarvester();
        Farm farm = new Farm(new User(), 1, 0);
        Harvester harvester = new Harvester();
        
        //ID
        farmHarvester.setId(null);
        assertEquals(null, farmHarvester.getId());

        //farm
        farmHarvester.setFarm(farm);
        assertEquals(farm, farmHarvester.getFarm());

        //harvester
        farmHarvester.setHarvester(harvester);
        assertEquals(harvester, farmHarvester.getHarvester());

        //quantity
        farmHarvester.setQuantity(10);
        assertEquals(10, farmHarvester.getQuantity());


    }
    
}