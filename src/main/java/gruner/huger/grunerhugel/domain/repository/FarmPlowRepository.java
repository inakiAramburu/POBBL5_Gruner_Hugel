package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmPlow;
import gruner.huger.grunerhugel.model.compositekey.FarmPlowId;

public interface FarmPlowRepository extends CrudRepository<FarmPlow, FarmPlowId> {

    Iterable<FarmPlow> findByFarm(Farm farm);
}
