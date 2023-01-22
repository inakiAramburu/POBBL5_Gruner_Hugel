package gruner.huger.grunerhugel.modelTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import gruner.huger.grunerhugel.model.Town;
import gruner.huger.grunerhugel.model.Weather;

public class WeatherTest {
    @Test
    public void testGetSet(){

        Weather weather = new Weather();
        Date date =new Date();
        Town town =new Town();
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
        assertEquals(weather.getDateTime(), date);
        assertEquals(weather.getTemperature(), 2, 0.0);
        assertEquals(weather.getDirectRadiation(), 2, 0.0);
        assertEquals(weather.getRain(), 2, 0.0);
        assertEquals(weather.getSnowfall(), 2, 0.0);
        assertEquals(weather.getCloudcover(), 2, 0.0);
        assertEquals(weather.getPrecipitation(), 2, 0.0);
        assertEquals(weather.getWindspeed(), 2, 0.0);
        assertEquals(weather.getSoilTemperature(), 2, 0.0);
        assertEquals(weather.getSoilTemperature2(), 2, 0.0);
        assertEquals(weather.getSoilMoisture(), 2, 0.0);
        assertEquals(weather.getSoilMoisture2(), 2, 0.0);
        assertEquals(weather.getTown(), town);
       }
       @Test
    public void testToString(){
        Weather weather = new Weather();
        Date date =new Date();
        Town town =new Town();
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
