package gruner.huger.grunerhugel.modelTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmPlow;
import gruner.huger.grunerhugel.model.Plow;
import gruner.huger.grunerhugel.model.compositekey.FarmPlowId;
import jakarta.persistence.EmbeddedId;

@SpringBootTest
public class FarmPlowTest {

    @EmbeddedId
    private FarmPlowId id;

    @Test
    public void costructorTest() {
        Farm farm = new Farm();
        Plow plow = new Plow();
        FarmPlow farmPlow = new FarmPlow(farm, plow, 0);
        assertEquals(farmPlow.getFarm(), farm);
        assertEquals(farmPlow.getPlow(), plow);
        assertEquals(0, farmPlow.getQuantity());
    }

    @Test
    public void testGetSet() {
        Farm farm = new Farm();
        Plow plow = new Plow();
        FarmPlow farmPlow = new FarmPlow();
        farmPlow.setFarm(farm);
        farmPlow.setPlow(plow);
        farmPlow.setQuantity(10);
        farmPlow.setId(id);
        assertEquals(farmPlow.getId(), id);
        assertEquals(farmPlow.getFarm(), farm);
        assertEquals(farmPlow.getPlow(), plow);
        assertEquals(10, farmPlow.getQuantity());

        // toString
        assertEquals(farmPlow.toString(), "FarmPlow [farm=" + farm + ", plow=" + plow + ", quantity=" + 10 + "]");
    }

}
