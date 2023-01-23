package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.Town;
import gruner.huger.grunerhugel.model.Weather;

@SpringBootTest
class TownTest {
    @Test
    void testGetSet() {
        Town town = new Town();
        town.setId(5);
        town.setName("name");
        List<Weather> weather = new ArrayList<>();
        town.setWeather(weather);
        town.setLatitude(1.0);
        town.setLongitude(1.0);
        assertEquals(5, town.getId());
        assertEquals("name", town.getName());
        assertEquals(weather, town.getWeather());
        assertEquals(1.0, town.getLatitude(), 0.0);
        assertEquals(1.0, town.getLongitude(), 0.0);
    }

    @Test
    void testToString() {
        Town town = new Town();
        town.setId(5);
        town.setName("name");
        List<Weather> weather = new ArrayList<>();
        town.setWeather(weather);
        town.setLatitude(1.0);
        town.setLongitude(1.0);
        System.out.println(town.toString());
        assertEquals("Town [id=5, name=name, latitude=1.0, longitude=1.0]", town.toString());
    }
}
