package gruner.huger.grunerhugel.domain.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username =:username")
    User findByUsername(@Param("username") String username);
}
