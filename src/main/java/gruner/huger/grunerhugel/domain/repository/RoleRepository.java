package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findById(int id);
}
