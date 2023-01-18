package gruner.huger.grunerhugel.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Plant;

public interface PlantRepository extends CrudRepository<Plant, Integer> {

    Iterable<Plant> findByLand(Optional<Land> land);

}
