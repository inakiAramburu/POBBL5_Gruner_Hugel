package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gruner.huger.grunerhugel.model.Weather;

public interface WeatherRepository extends JpaRepository<Weather,String>{
    
}
