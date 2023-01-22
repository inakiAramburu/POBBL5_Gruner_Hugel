package gruner.huger.grunerhugel.modelTest;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;



import gruner.huger.grunerhugel.model.FarmPlow;
import gruner.huger.grunerhugel.model.Plow;
public class PlowTest {
    @Test
    public void testGetSet(){
        Plow plow =new Plow();

        plow.setPlowName("name");
        plow.setPrice(10);
        plow.setMaintenance(2);
        plow.setWorkingAmplitude(2);
        plow.setRecommendedPower(2);
        List<FarmPlow> farmPlows = new ArrayList<FarmPlow>();
        plow.setFarms(farmPlows);
        assertEquals(plow.getPlowName(), "name");
        assertEquals(plow.getPrice(), 10);
        assertEquals(plow.getMaintenance(), 2);
        assertEquals(plow.getWorkingAmplitude(), 2);
        assertEquals(plow.getRecommendedPower(), 2);
        assertEquals(plow.getFarms(), farmPlows);
    }

    @Test
    public void testString(){
        Plow plow =new Plow();
        plow.setPlowName("name");
        plow.setPrice(10);
        plow.setMaintenance(2);
        plow.setWorkingAmplitude(2);
        plow.setRecommendedPower(2);
        List<FarmPlow> farmPlows = new ArrayList<FarmPlow>();
        plow.setFarms(farmPlows);
        assertEquals("Plow [name=name, price=10, maintenance=2, workingAmplitude=2, recommendedPower=2]",plow.toString());   }
}
