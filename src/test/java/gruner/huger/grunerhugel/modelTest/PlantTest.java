package gruner.huger.grunerhugel.modelTest;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.OptimalConditions;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Weather;

public class PlantTest {
    @Test
    public void costructorTest(){
        OptimalConditions optimalConditions=new OptimalConditions();
        Land land =new Land();
        Plant plant = new Plant(optimalConditions, land);
        assertEquals(plant.getOptimalConditions(), optimalConditions);
        assertEquals(plant.getLand(), land);
    }

    @Test
    public void testcheck(){
        OptimalConditions optimalConditions=new OptimalConditions();
        Land land =new Land();
        Plant plant = new Plant(optimalConditions, land);
        Weather weather= new Weather();
        assertEquals(false,plant.check(weather));
    }
    @Test
    public void testGetSet(){
        OptimalConditions optimalConditions=new OptimalConditions();
        Land land =new Land();
        Plant plant = new Plant();
        plant.setId(0);
        plant.setName("name");
        plant.setStatus("GERMINATION");
        plant.setHealthPoint(100); 
        plant.setOptimalConditions(optimalConditions);
        plant.setLand(land);
        assertEquals(plant.getId(), 0);
        assertEquals(plant.getName(), "name");
        assertEquals(plant.getStatus(), "GERMINATION");
        assertEquals(plant.getHealthPoint(), 100);
        assertEquals(plant.getOptimalConditions(), optimalConditions);
        assertEquals(plant.getLand(), land);
        
        plant.loseHealth();
        assertEquals(plant.getHealthPoint(), 95);
        }
    @Test 
    public void testGrowPlant(){
        Plant plant = new Plant();
        plant.setStatus("GERMINATION");
        plant.growPlant();
        assertEquals(plant.getStatus(), "VEGETATIVE");
        plant.setStatus("VEGETATIVE");
        plant.growPlant();
        assertEquals(plant.getStatus(), "TILLERING");
        plant.setStatus("TILLERING");
        plant.growPlant();
        assertEquals(plant.getStatus(), "ANTHESIS");
        plant.setStatus("ANTHESIS");
        plant.growPlant();
        assertEquals(plant.getStatus(), "MILKY");
        plant.setStatus("MILKY");
        plant.growPlant();
        assertEquals(plant.getStatus(), "PASTY");
        plant.setStatus("PASTY");
        plant.growPlant();
        assertEquals(plant.getStatus(), "MATURATION");
        plant.setStatus("MATURATION");
        plant.growPlant();
        assertEquals(plant.getStatus(), "DEAD");
        plant.setStatus("DEAD");
        plant.growPlant();
        assertEquals(plant.getStatus(), "DEAD");
}
    @Test
    public void testcheckOptimalCondition(){
        Plant plant =new Plant();
        Weather weather= new Weather();
        
        //false
        plant.checkOptimalCondition(weather);
        assertEquals(plant.getHealthPoint(), -5);

        //true
/* 
        weather.setTemperature(21);
        plant.setStatus("VEGETATIVE");
        plant.checkOptimalCondition(weather);

        System.out.println(plant.getHealthPoint());
*/
    }
            
    @Test
    public void testString(){
            OptimalConditions optimalConditions=new OptimalConditions();
            Land land =new Land();
            Plant plant = new Plant(optimalConditions, land);
            System.out.println(plant.toString());
            }
        
}
