package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import gruner.huger.grunerhugel.model.FarmPlow;
import gruner.huger.grunerhugel.model.Plow;

@SpringBootTest
class PlowTest {
    @Test
    void testGetSet() {
        Plow plow = new Plow();

        plow.setPlowName("name");
        plow.setPrice(10);
        plow.setMaintenance(2);
        plow.setWorkingAmplitude(2);
        plow.setRecommendedPower(2);
        List<FarmPlow> farmPlows = new ArrayList<FarmPlow>();
        plow.setFarms(farmPlows);
        assertEquals("name", plow.getPlowName());
        assertEquals(10, plow.getPrice());
        assertEquals(2, plow.getMaintenance());
        assertEquals(2, plow.getWorkingAmplitude());
        assertEquals(2, plow.getRecommendedPower());
        assertEquals(plow.getFarms(), farmPlows);
    }

    @Test
    void testString() {
        Plow plow = new Plow();
        plow.setPlowName("name");
        plow.setPrice(10);
        plow.setMaintenance(2);
        plow.setWorkingAmplitude(2);
        plow.setRecommendedPower(2);
        List<FarmPlow> farmPlows = new ArrayList<FarmPlow>();
        plow.setFarms(farmPlows);
        assertEquals("Plow [name=name, price=10, maintenance=2, workingAmplitude=2, recommendedPower=2]",
                plow.toString());
    }
}
