package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.FarmPlow;
import gruner.huger.grunerhugel.model.compositeKeys.FarmPlowId;

public interface FarmPlowRepository extends CrudRepository<FarmPlow,FarmPlowId>{
    
}
