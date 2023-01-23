package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.OptimalConditions;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Weather;

@SpringBootTest
public class PlantTest {
    @Test
    public void costructorTest() {
        OptimalConditions optimalConditions = new OptimalConditions();
        Land land = new Land();
        Plant plant = new Plant(optimalConditions, land);
        assertEquals(plant.getOptimalConditions(), optimalConditions);
        assertEquals(plant.getLand(), land);
    }

    @Test
    public void testcheck() {

        OptimalConditions optimalConditions = new OptimalConditions();
        Land land = new Land();
        Plant plant = new Plant(optimalConditions, land);
        Weather weather = new Weather();

        plant.check(weather);
        assertEquals("GERMINATION", plant.getStatus());
        plant.setStatus("VEGETATIVE");
        plant.check(weather);
        assertEquals("VEGETATIVE", plant.getStatus());
        plant.setStatus("TILLERING");
        plant.check(weather);
        assertEquals("TILLERING", plant.getStatus());
        plant.setStatus("ANTHESIS");
        plant.check(weather);
        assertEquals("ANTHESIS", plant.getStatus());
        plant.setStatus("MILKY");
        plant.check(weather);
        assertEquals("MILKY", plant.getStatus());
        plant.setStatus("PASTY");
        plant.check(weather);
        assertEquals("PASTY", plant.getStatus());

    }

    @Test
    public void testGetSet() {
        OptimalConditions optimalConditions = new OptimalConditions();
        Land land = new Land();
        Plant plant = new Plant();
        plant.setId(0);
        plant.setName("name");
        plant.setStatus("GERMINATION");
        plant.setHealthPoint(100);
        plant.setOptimalConditions(optimalConditions);
        plant.setGrowthRate(200);
        plant.setLand(land);
        assertEquals(200, plant.getGrowthRate());
        assertEquals(0, plant.getId());
        assertEquals("name", plant.getName());
        assertEquals("GERMINATION", plant.getStatus());
        assertEquals(100, plant.getHealthPoint());
        assertEquals(plant.getOptimalConditions(), optimalConditions);
        assertEquals(plant.getLand(), land);

        plant.loseHealth();
        assertEquals(95, plant.getHealthPoint());
        plant.setHealthPoint(0);
        plant.loseHealth();
        assertEquals("DEAD", plant.getStatus());
    }

    @Test
    public void testGrowPlant() {
        OptimalConditions optimalConditions = new OptimalConditions();
        Land land = new Land();
        Plant plant = new Plant(optimalConditions, land);
        plant.setGrowthRate(100);
        plant.growPlant();
        assertEquals("VEGETATIVE", plant.getStatus());
        plant.setGrowthRate(200);
        plant.growPlant();
        assertEquals("TILLERING", plant.getStatus());
        plant.setGrowthRate(300);
        plant.growPlant();
        assertEquals("ANTHESIS", plant.getStatus());
        plant.setGrowthRate(400);
        plant.growPlant();
        assertEquals("MILKY", plant.getStatus());
        plant.setGrowthRate(500);
        plant.growPlant();
        assertEquals("PASTY", plant.getStatus());
        plant.setGrowthRate(600);
        plant.growPlant();
        assertEquals("MATURATION", plant.getStatus());
        plant.setGrowthRate(700);
        plant.growPlant();
        assertEquals("GERMINATION", plant.getStatus());
        plant.setGrowthRate(800);
        plant.growPlant();
    }

    /*
     * @Test
     * public void testcheckOptimalCondition() {
     * Plant plant = new Plant();
     * Weather weather = new Weather();
     * 
     * // false
     * plant.checkOptimalCondition(weather);
     * assertEquals(plant.getHealthPoint(), -5);
     * 
     * // true
     * /*
     * weather.setTemperature(21);
     * plant.setStatus("VEGETATIVE");
     * plant.checkOptimalCondition(weather);
     * 
     * System.out.println(plant.getHealthPoint());
     * }
     */

    @Test
    public void testString() {
        OptimalConditions optimalConditions = new OptimalConditions();
        Land land = new Land();
        Plant plant = new Plant(optimalConditions, land);
        System.out.println(plant.toString());
    }

}
