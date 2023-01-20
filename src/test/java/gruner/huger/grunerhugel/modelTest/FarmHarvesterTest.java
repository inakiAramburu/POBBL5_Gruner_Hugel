package gruner.huger.grunerhugel.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.Harvester;
import gruner.huger.grunerhugel.model.compositekey.FarmHarvesterId;

public class FarmHarvesterTest {

    @Test
    public void givenValidData_WhenConstructorCalled_ThenObjectCreated() {
        FarmHarvester farmHarvester = new FarmHarvester(new Farm(), new Harvester(), 10);
        //assertEquals(1, farmHarvester.getFarm().getId());
        //assertEquals("Harvester1", farmHarvester.getHarvester().getHarvesterName());
        //assertEquals(10, farmHarvester.getQuantity());
    }

/*
@Test
    public void testGetsSets() {
// setHarvester setFarm setId getQuantity
        FarmHarvester farmHarvester = new FarmHarvester();
        farmHarvester.setHarvester(new Harvester());
        farmHarvester.setFarm(new Farm());
        farmHarvester.setId(new FarmHarvesterId());
        farmHarvester.setQuantity(10);

        assertEquals(1, farmHarvester.getFarm().getId());
        assertEquals("Harvester1", farmHarvester.getHarvester().getHarvesterName());
        assertEquals(1, farmHarvester.getId().getFarmId());
        assertEquals("Harvester1", farmHarvester.getId().getFarmId());
        assertEquals(10, farmHarvester.getQuantity());
    }*/
}