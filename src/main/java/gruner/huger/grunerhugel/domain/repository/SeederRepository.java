package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.Seeder;

@Repository
public interface SeederRepository extends CrudRepository<Seeder,String>{
    
    @Query("SELECT u FROM Seeder u WHERE u.seederName =:seederName")
    Seeder findByName(@Param("seederName") String name);
}
