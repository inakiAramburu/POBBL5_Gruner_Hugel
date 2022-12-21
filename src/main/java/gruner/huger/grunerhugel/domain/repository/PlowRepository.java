package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gruner.huger.grunerhugel.model.Plow;

public interface PlowRepository extends JpaRepository<Plow,String>{
    
    @Query("SELECT u FROM Plow u WHERE u.name =:name")
    Plow findbyName(@Param("name") String name);
}
