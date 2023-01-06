package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.FarmSeeder;
import gruner.huger.grunerhugel.model.compositekey.FarmSeederId;

@Repository
public interface FarmSeederRepository extends CrudRepository<FarmSeeder,FarmSeederId>{
    
}
