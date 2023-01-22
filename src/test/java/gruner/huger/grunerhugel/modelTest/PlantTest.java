package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.OptimalConditions;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Weather;

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
        assertEquals(plant.getStatus(), "GERMINATION");
        plant.setStatus("VEGETATIVE");
        plant.check(weather);
        assertEquals(plant.getStatus(), "VEGETATIVE");
        plant.setStatus("TILLERING");
        plant.check(weather);
        assertEquals(plant.getStatus(), "TILLERING");
        plant.setStatus("ANTHESIS");
        plant.check(weather);
        assertEquals(plant.getStatus(), "ANTHESIS");
        plant.setStatus("MILKY");
        plant.check(weather);
        assertEquals(plant.getStatus(), "MILKY");
        plant.setStatus("PASTY");
        plant.check(weather);
        assertEquals(plant.getStatus(), "PASTY");

        


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
        assertEquals(plant.getId(), 0);
        assertEquals(plant.getName(), "name");
        assertEquals(plant.getStatus(), "GERMINATION");
        assertEquals(plant.getHealthPoint(), 100);
        assertEquals(plant.getOptimalConditions(), optimalConditions);
        assertEquals(plant.getLand(), land);

        plant.loseHealth();
        assertEquals(plant.getHealthPoint(), 95);
        plant.setHealthPoint(0);
        plant.loseHealth();
        assertEquals( "DEAD",plant.getStatus());
    }
       
   @Test
    public void testGrowPlant() {
        OptimalConditions optimalConditions = new OptimalConditions();
        Land land = new Land();
        Plant plant = new Plant(optimalConditions, land);
        plant.setGrowthRate(100);
        plant.growPlant();
        assertEquals(plant.getStatus(), "VEGETATIVE");
        plant.setGrowthRate(200);
        plant.growPlant();
        assertEquals(plant.getStatus(), "TILLERING");
        plant.setGrowthRate(300);
        plant.growPlant();
        assertEquals(plant.getStatus(), "ANTHESIS");
        plant.setGrowthRate(400);
        plant.growPlant();
        assertEquals(plant.getStatus(), "MILKY");
        plant.setGrowthRate(500);
        plant.growPlant();
        assertEquals(plant.getStatus(), "PASTY");
        plant.setGrowthRate(600);
        plant.growPlant();
        assertEquals(plant.getStatus(), "MATURATION");
        plant.setGrowthRate(700);
        plant.growPlant();
        assertEquals(plant.getStatus(), "GERMINATION");
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
