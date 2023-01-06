package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
    User findByUsername(String username);
}
