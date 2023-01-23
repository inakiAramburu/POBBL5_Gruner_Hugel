package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import gruner.huger.grunerhugel.model.FarmSeeder;
import gruner.huger.grunerhugel.model.Seeder;

@SpringBootTest
public class SeederTest {
    @Test
    public void testGetSet() {
        Seeder seeder = new Seeder();
        seeder.setSeederName("name");
        seeder.setPrice(5);
        seeder.setMaintenance(2);
        seeder.setPlantType("PlantType");
        seeder.setCapacity(2);
        seeder.setWorkingAmplitude(2);
        seeder.setRecommendedPower(2);
        seeder.setFertilized(true);
        seeder.setFrontalSubjection("FrontalSubjection");
        List<FarmSeeder> farms = new ArrayList<>();
        seeder.setFarms(farms);
        assertEquals("name", seeder.getSeederName());
        assertEquals(5, seeder.getPrice());
        assertEquals(2, seeder.getMaintenance());
        assertEquals("PlantType", seeder.getPlantType());
        assertEquals(2, seeder.getCapacity());
        assertEquals(2, seeder.getWorkingAmplitude());
        assertEquals(2, seeder.getRecommendedPower());
        assertEquals(true, seeder.isFertilized());
        assertEquals("FrontalSubjection", seeder.getFrontalSubjection());
        assertEquals(farms, seeder.getFarms());
    }

    @Test
    public void testToString() {
        Seeder seeder = new Seeder();
        seeder.setSeederName("name");
        seeder.setPrice(5);
        seeder.setMaintenance(2);
        seeder.setPlantType("PlantType");
        seeder.setCapacity(2);
        seeder.setWorkingAmplitude(2);
        seeder.setRecommendedPower(2);
        seeder.setFertilized(true);
        seeder.setFrontalSubjection("FrontalSubjection");
        List<FarmSeeder> farms = new ArrayList<>();
        seeder.setFarms(farms);
        assertEquals(
                "Seeder [name=name, price=5, maintenance=2, plantType=PlantType, capacity=2, workingAmplitude=2, recommendedPower=2, fertilized=true, frontalSubjection=FrontalSubjection, farms=[]]",
                seeder.toString());

    }
}
