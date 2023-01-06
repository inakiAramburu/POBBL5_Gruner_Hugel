package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.User;

@Repository
public interface FarmRepository extends CrudRepository<Farm,Integer>{

    Farm findByUser(User user);
}
