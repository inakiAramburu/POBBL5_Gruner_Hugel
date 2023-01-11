package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Simulation;

public interface SimulationRepository extends CrudRepository<Simulation, Integer> {

    Simulation findByFarm(Farm farm);

}
