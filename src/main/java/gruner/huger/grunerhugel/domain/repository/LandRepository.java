package gruner.huger.grunerhugel.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Land;

public interface LandRepository extends CrudRepository<Land, Integer> {

    List<Land> findByFarm(Farm farm);
}
