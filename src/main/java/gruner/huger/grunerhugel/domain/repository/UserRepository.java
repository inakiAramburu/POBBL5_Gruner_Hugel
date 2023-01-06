package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    
    User findByUsername(String username);
}
