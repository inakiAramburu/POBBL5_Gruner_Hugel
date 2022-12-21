package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import gruner.huger.grunerhugel.model.Tractor;

public interface TractorRepository extends CrudRepository<Tractor,String>{
    
    @Query("SELECT u FROM Tractor u WHERE u.name =:name")
    Tractor findbyName(@Param("name") String name);
}
