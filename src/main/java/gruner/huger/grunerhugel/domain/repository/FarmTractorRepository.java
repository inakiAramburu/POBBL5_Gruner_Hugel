package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmTractor;
import gruner.huger.grunerhugel.model.compositekey.FarmTractorId;

public interface FarmTractorRepository extends CrudRepository<FarmTractor, FarmTractorId> {

    Iterable<FarmTractor> findByFarm(Farm farm);
}
