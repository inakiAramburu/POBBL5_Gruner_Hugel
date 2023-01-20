package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Land;

public interface LandRepository extends CrudRepository<Land, Integer> {

    Iterable<Land> findByFarm(Farm farm);
}
