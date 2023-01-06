package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.Plant;

@Repository
public interface PlantRepository extends CrudRepository<Plant,Integer>{
    
}
