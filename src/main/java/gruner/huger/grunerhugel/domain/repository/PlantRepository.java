package gruner.huger.grunerhugel.domain.repository;

import java.util.LinkedHashMap;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Plant;

public interface PlantRepository extends CrudRepository<Plant, Integer> {

    Iterable<Plant> searchByLand(Land land);

    Iterable<Plant> searchByLand(Optional<Land> land);

    LinkedHashMap<String, Object> findByLand(Land land);
}
