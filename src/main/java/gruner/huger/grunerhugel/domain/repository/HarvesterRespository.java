package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gruner.huger.grunerhugel.model.Harvester;

public interface HarvesterRespository extends JpaRepository<Harvester,String>{
    
}
