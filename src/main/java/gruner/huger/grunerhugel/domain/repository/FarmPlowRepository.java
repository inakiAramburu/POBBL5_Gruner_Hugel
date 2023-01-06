package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.FarmPlow;
import gruner.huger.grunerhugel.model.compositekey.FarmPlowId;

@Repository
public interface FarmPlowRepository extends CrudRepository<FarmPlow,FarmPlowId>{
    
}
