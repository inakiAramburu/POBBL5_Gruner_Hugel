package gruner.huger.grunerhugel.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.Harvester;

@SpringBootTest
class HarvesterTest {
    @Test
    void testGetSet() {
        Harvester harvester = new Harvester();

        harvester.setPrice(5);
        assertEquals(5, harvester.getPrice());
        harvester.setMaintenance(3);
        assertEquals(3, harvester.getMaintenance());
        harvester.setPower(2);
        assertEquals(2, harvester.getPower());
        harvester.setMaxSpeed(2);
        assertEquals(2, harvester.getMaxSpeed());
        harvester.setFuelCapacity(2);
        assertEquals(2, harvester.getFuelCapacity());
        harvester.setCultivationCapacity(2);
        assertEquals(2, harvester.getCultivationCapacity());
        List<FarmHarvester> farms = new ArrayList<>();
        harvester.setFarms(farms);
        assertEquals(farms, harvester.getFarms());
    }

    @Test
    void hashCodeTest() {
        Harvester harvester = new Harvester();
        Harvester harvester2 = new Harvester();

        // true
        assertEquals(harvester.hashCode(), harvester2.hashCode());
        assertEquals(harvester.hashCode(), harvester.hashCode());
    }

    @Test
    void equalsTest() {
        Harvester harvester = new Harvester();
        Harvester harvester2 = new Harvester();
        Harvester harvester3 = new Harvester();
        harvester3.setPrice(5);
        // true
        assertEquals(harvester, harvester2);
        assertEquals(harvester, harvester);

        // false
        assertNotEquals(harvester, harvester3);
        assertNotEquals(harvester, null);
    }

    @Test
    void toStringTest() {
        Harvester harvester = new Harvester();
        harvester.setPrice(5);
        harvester.setMaintenance(3);
        harvester.setPower(2);
        harvester.setMaxSpeed(2);
        harvester.setFuelCapacity(2);
        harvester.setCultivationCapacity(2);
        assertEquals(
                "Harvester [harvesterName=null, price=5, maintenance=3, power=2, maxSpeed=2, fuelCapacity=2, cultivationCapacity=2, farms=null]",
                harvester.toString());
    }
}
