package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.Land;

@Repository
public interface LandRepository extends CrudRepository<Land,Integer>{
    
}
