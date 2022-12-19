package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.compositeKeys.FarmHarvesterId;

public interface FarmHarvesterRepository extends CrudRepository<FarmHarvester,FarmHarvesterId>{
    
}
