package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmSeeder;
import gruner.huger.grunerhugel.model.compositekey.FarmSeederId;

public interface FarmSeederRepository extends CrudRepository<FarmSeeder, FarmSeederId> {

    Iterable<FarmSeeder> findByFarm(Farm farm);
}
