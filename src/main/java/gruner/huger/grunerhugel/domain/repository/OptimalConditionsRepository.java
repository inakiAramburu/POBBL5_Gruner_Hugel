package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.OptimalConditions;

@Repository
public interface OptimalConditionsRepository extends CrudRepository<OptimalConditions,String>{
    
}
