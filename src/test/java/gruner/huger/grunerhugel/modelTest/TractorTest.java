package gruner.huger.grunerhugel.modelTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.FarmTractor;
import gruner.huger.grunerhugel.model.Tractor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TractorTest {
    @Test
    void testGetSet() {
        Tractor tractor = new Tractor();
        tractor.setTractorName("name");
        tractor.setPrice(2);
        tractor.setMaintenance(2);
        tractor.setPower(2);
        tractor.setMaxSpeed(2);
        tractor.setFuelCapacity(2);
        List<FarmTractor> farm = new ArrayList<>();
        tractor.setFarms(farm);
        assertEquals("name", tractor.getTractorName());
        assertEquals(2, tractor.getPrice());
        assertEquals(2, tractor.getMaintenance());
        assertEquals(2, tractor.getPower());
        assertEquals(2, tractor.getMaxSpeed());
        assertEquals(2, tractor.getFuelCapacity());
        assertEquals(farm, tractor.getFarms());
    }

    @Test
    void testToString() {
        Tractor tractor = new Tractor();
        tractor.setTractorName("name");
        tractor.setPrice(2);
        tractor.setMaintenance(2);
        tractor.setPower(2);
        tractor.setMaxSpeed(2);
        tractor.setFuelCapacity(2);
        List<FarmTractor> farm = new ArrayList<>();
        tractor.setFarms(farm);
        assertEquals("Tractor [name=name, price=2, maintenance=2, power=2, maxSpeed=2, fuelCapacity=2]",
                tractor.toString());
    }
}
