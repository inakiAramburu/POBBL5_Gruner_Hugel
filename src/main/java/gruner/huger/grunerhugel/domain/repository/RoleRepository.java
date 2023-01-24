package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findById(int id);
}
