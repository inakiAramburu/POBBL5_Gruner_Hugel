import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.Harvester;

public class FarmHarvesterTest {

    @Test
    public void givenValidData_WhenConstructorCalled_ThenObjectCreated() {
        FarmHarvester farmHarvester = new FarmHarvester(new Farm(1), new Harvester("Harvester1"), 10);
        assertEquals(1, farmHarvester.getFarm().getId());
        assertEquals("Harvester1", farmHarvester.getHarvester().getHarvesterName());
        assertEquals(10, farmHarvester.getQuantity());
    }


@Test
    public void testGetsSets() {
// setHarvester setFarm setId getQuantity
        FarmHarvester farmHarvester = new FarmHarvester();
        farmHarvester.setHarvester(new Harvester("Harvester1"));
        farmHarvester.setFarm(new Farm(1));
        farmHarvester.setId(new FarmHarvesterId(1, "Harvester1"));
        farmHarvester.setQuantity(10);

        assertEquals(1, farmHarvester.getFarm().getId());
        assertEquals("Harvester1", farmHarvester.getHarvester().getHarvesterName());
        assertEquals(1, farmHarvester.getId().getFarmId());
        assertEquals("Harvester1", farmHarvester.getId().getHarvesterName());
        assertEquals(10, farmHarvester.getQuantity());
    }
}