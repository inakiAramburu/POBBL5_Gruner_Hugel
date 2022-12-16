package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.compositeKeys.FarmHarvesterId;

@Repository
public interface FarmHarvesterRepository extends CrudRepository<FarmHarvester,FarmHarvesterId>{
    
}
