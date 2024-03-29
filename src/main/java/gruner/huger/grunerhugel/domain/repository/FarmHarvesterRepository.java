package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.compositekey.FarmHarvesterId;

public interface FarmHarvesterRepository extends CrudRepository<FarmHarvester, FarmHarvesterId> {

    Iterable<FarmHarvester> findByFarm(Farm farm);

}
