package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.FarmTractor;
import gruner.huger.grunerhugel.model.compositekey.FarmTractorId;

@Repository
public interface FarmTractorRepository extends CrudRepository<FarmTractor,FarmTractorId>{
    
}
