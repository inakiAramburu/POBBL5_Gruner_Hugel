package gruner.huger.grunerhugel.modelTest;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.formobjects.CreateLand;

public class CreateLandTest {
    @Test
    public void testGetSet() {
        CreateLand createLand =new CreateLand();
        createLand.setPlantName("nama");
        createLand.setTown("100");
        createLand.setLatitude("100");
        createLand.setLongitude("100");
        createLand.setSize(10.0);

        assertEquals(createLand.getPlantName(), "nama");
        assertEquals(createLand.getTown(), "100");
        assertEquals(createLand.getLatitude(), "100");
        assertEquals(createLand.getLongitude(), "100");
        assertEquals(createLand.getSize(), 10.0, 0.0);
        

    }
}
