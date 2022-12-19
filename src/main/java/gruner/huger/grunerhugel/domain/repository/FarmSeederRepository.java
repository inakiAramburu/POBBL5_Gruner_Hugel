package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.FarmSeeder;
import gruner.huger.grunerhugel.model.compositeKeys.FarmSeederId;

public interface FarmSeederRepository extends CrudRepository<FarmSeeder,FarmSeederId>{
    
}
