package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.Harvester;

@Repository
public interface HarvesterRespository extends CrudRepository<Harvester,String>{

    @Query("SELECT u FROM Harvester u WHERE u.harvesterName =:harvesterName")
    Harvester findByName(@Param("harvesterName") String name);
}
