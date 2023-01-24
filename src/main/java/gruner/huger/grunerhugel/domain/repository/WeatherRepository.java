package gruner.huger.grunerhugel.domain.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gruner.huger.grunerhugel.model.Weather;

public interface WeatherRepository extends JpaRepository<Weather, String> {

    @Query(value = "SELECT * FROM weather WHERE village = ?1 AND dateTime = ?2", nativeQuery = true)
    Weather findByVillageAndWeather(@Param("village") String village, @Param("dateTime") Date dateTime);
}
