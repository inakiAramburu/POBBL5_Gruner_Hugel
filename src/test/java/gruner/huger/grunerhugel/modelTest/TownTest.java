package gruner.huger.grunerhugel.modelTest;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Town;
import gruner.huger.grunerhugel.model.Weather;
public class TownTest {
    @Test
    public void testGetSet(){
        Town town = new Town();
        town.setId(5);
        town.setName("name");
        List<Weather> weather =new ArrayList<>();
        town.setWeather(weather);
        town.setLatitude(1.0);
        town.setLongitude(1.0);
        assertEquals(town.getId(), 5);
        assertEquals(town.getName(), "name");
        assertEquals(town.getWeather(), weather);
        assertEquals(town.getLatitude(), 1.0, 0.0);
        assertEquals(town.getLongitude(), 1.0, 0.0);
    }


    @Test
    public void testToString(){
        Town town = new Town();
        town.setId(5);
        town.setName("name");
        List<Weather> weather =new ArrayList<>();
        town.setWeather(weather);
        town.setLatitude(1.0);
        town.setLongitude(1.0);
        System.out.println(town.toString());
        assertEquals("Town [id=5, name=name, latitude=1.0, longitude=1.0]", town.toString());
    }
}
