package gruner.huger.grunerhugel.domain.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Plant;

public interface PlantRepository extends CrudRepository<Plant, Integer> {

    List<Plant> findByLand(Land land);
}
