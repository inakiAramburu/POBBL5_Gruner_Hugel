package gruner.huger.grunerhugel.domain.role;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    @Query("SELECT u FROM Role u WHERE u.id =:id")
    Role findById(@Param("id") int id);
}