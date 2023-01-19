package gruner.huger.grunerhugel.domain.repository;

import java.time.Year;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Fuel;
import gruner.huger.grunerhugel.model.compositekey.FuelId;

public interface FuelRepository extends CrudRepository<Fuel, FuelId> {

    @Query(value = "SELECT * FROM fuel WHERE year=?1 and `Period (Week)` = ?2", nativeQuery = true)
    Optional<Fuel> findByYearAndPeriod(Year year, int week);

    @Query(value = "SELECT * FROM fuel WHERE price > ?1", nativeQuery = true)
    Iterable<Fuel> findByPriceGreaterThan(int price);

    @Query(value = "SELECT * FROM fuel WHERE price < ?1", nativeQuery = true)
    Iterable<Fuel> findByPriceSmallerThan(int price);
}
