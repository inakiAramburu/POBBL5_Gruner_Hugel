package gruner.huger.grunerhugel.domain.weather;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import gruner.huger.grunerhugel.domain.repository.WeatherRepository;
import gruner.huger.grunerhugel.model.Weather;

@Service
public class WeatherDetailServiceImpl {

    @Autowired
    private WeatherRepository weatherRepository;

    public Weather loadWeatherByVillageAndDateTime(String village, String dateTime) throws NotFoundException {
        Weather weather = weatherRepository.findByVillageAndWeather(village, dateTime);
        if(weather == null){
            throw new NotFoundException();
        }
        return weather;
    }
    
}
