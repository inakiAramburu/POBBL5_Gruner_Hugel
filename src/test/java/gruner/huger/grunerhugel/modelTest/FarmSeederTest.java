package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmSeeder;
import gruner.huger.grunerhugel.model.Seeder;
import gruner.huger.grunerhugel.model.compositekey.FarmSeederId;
import jakarta.persistence.EmbeddedId;

@SpringBootTest
class FarmSeederTest {

    @EmbeddedId
    private FarmSeederId id;

    @Test
    void costructorTest() {
        Farm farm = new Farm();
        Seeder seeder = new Seeder();

        FarmSeeder farmSeeder = new FarmSeeder(farm, seeder, 10);
        assertEquals(farmSeeder.getFarm(), farm);
        assertEquals(farmSeeder.getSeeder(), seeder);
        assertEquals(10, farmSeeder.getQuantity());
    }

    @Test
    void testGetSet() {
        FarmSeeder farmSeeder = new FarmSeeder();
        Farm farm = new Farm();
        Seeder seeder = new Seeder();
        farmSeeder.setFarm(farm);
        farmSeeder.setSeeder(seeder);
        farmSeeder.setQuantity(10);
        farmSeeder.setId(id);
        assertEquals(farmSeeder.getId(), id);
        assertEquals(farmSeeder.getFarm(), farm);
        assertEquals(farmSeeder.getSeeder(), seeder);
        assertEquals(10, farmSeeder.getQuantity());

        // toString
        assertEquals(farmSeeder.toString(),
                "FarmSeeder [farm=" + farm + ", seeder=" + seeder + ", quantity=" + 10 + "]");
    }
}
