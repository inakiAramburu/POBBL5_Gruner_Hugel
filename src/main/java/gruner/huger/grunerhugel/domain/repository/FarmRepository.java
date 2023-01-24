package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.User;

public interface FarmRepository extends CrudRepository<Farm, Integer> {

    Farm findByUser(User user);
}
