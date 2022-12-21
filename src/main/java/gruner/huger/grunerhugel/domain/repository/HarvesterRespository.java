package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import gruner.huger.grunerhugel.model.Harvester;

public interface HarvesterRespository extends CrudRepository<Harvester,String>{

    @Query("SELECT u FROM Harvester u WHERE u.name =:name")
    Harvester findbyName(@Param("name") String name);
}
