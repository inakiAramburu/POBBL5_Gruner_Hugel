package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.model.Town;
import gruner.huger.grunerhugel.model.Weather;

@SpringBootTest
public class WeatherTest {
    @Test
    public void testGetSet() {

        Weather weather = new Weather();
        Date date = new Date();
        Town town = new Town();
        weather.setDateTime(date);
        weather.setTemperature(2);
        weather.setDirectRadiation(2);
        weather.setRain(2);
        weather.setSnowfall(2);
        weather.setCloudcover(2);
        weather.setPrecipitation(2);
        weather.setWindspeed(2);
        weather.setSoilTemperature(2);
        weather.setSoilTemperature2(2);
        weather.setSoilMoisture(2);
        weather.setSoilMoisture2(2);
        weather.setTown(town);
        assertEquals(date, weather.getDateTime());
        assertEquals(2, weather.getTemperature(), 0.0);
        assertEquals(2, weather.getDirectRadiation(), 0.0);
        assertEquals(2, weather.getRain(), 0.0);
        assertEquals(2, weather.getSnowfall(), 0.0);
        assertEquals(2, weather.getCloudcover(), 0.0);
        assertEquals(2, weather.getPrecipitation(), 0.0);
        assertEquals(2, weather.getWindspeed(), 0.0);
        assertEquals(2, weather.getSoilTemperature(), 0.0);
        assertEquals(2, weather.getSoilTemperature2(), 0.0);
        assertEquals(2, weather.getSoilMoisture(), 0.0);
        assertEquals(2, weather.getSoilMoisture2(), 0.0);
        assertEquals(town, weather.getTown());
    }

    @Test
    public void testToString() {
        Weather weather = new Weather();
        Date date = new Date();
        Town town = new Town();
        weather.setDateTime(date);
        weather.setTemperature(2);
        weather.setDirectRadiation(2);
        weather.setRain(2);
        weather.setSnowfall(2);
        weather.setCloudcover(2);
        weather.setPrecipitation(2);
        weather.setWindspeed(2);
        weather.setSoilTemperature(2);
        weather.setSoilTemperature2(2);
        weather.setSoilMoisture(2);
        weather.setSoilMoisture2(2);
        weather.setTown(town);
        System.out.println(weather.toString());
    }
}
