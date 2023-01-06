package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.Simulation;

@Repository
public interface SimulationRepository extends CrudRepository<Simulation,Integer>{
    
}
