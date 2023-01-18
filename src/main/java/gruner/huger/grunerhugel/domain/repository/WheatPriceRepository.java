package gruner.huger.grunerhugel.domain.repository;

import java.time.Year;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.WheatPrice;
import gruner.huger.grunerhugel.model.compositekey.WheatPriceId;

public interface WheatPriceRepository extends CrudRepository<WheatPrice, WheatPriceId> {

    @Query(value = "SELECT * FROM wheat_price WHERE year=?1 and month = ?2", nativeQuery = true)
    Iterable<WheatPrice> findByYearAndMonth(Year year, String month);

    @Query(value = "SELECT * FROM wheat_price WHERE `price (T/€)` > ?1", nativeQuery = true)
    Iterable<WheatPrice> findByPriceGreaterThan(int price);

    @Query(value = "SELECT * FROM wheat_price WHERE `price (T/€)` < ?1", nativeQuery = true)
    Iterable<WheatPrice> findByPriceSmallerThan(int price);
}
