package gruner.huger.grunerhugel.modelTest.formobjects;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.formobjects.CreateLand;

@SpringBootTest
public class CreateLandTest {
    @Test
    public void testGetSet() {
        CreateLand createLand = new CreateLand();
        createLand.setPlantName("nama");
        createLand.setTown("100");
        createLand.setLatitude("100");
        createLand.setLongitude("100");
        createLand.setSize(10.0);

        assertEquals("nama", createLand.getPlantName());
        assertEquals("100", createLand.getTown());
        assertEquals("100", createLand.getLatitude());
        assertEquals("100", createLand.getLongitude());
        assertEquals(10.0, createLand.getSize(), 0.0);

    }
}
