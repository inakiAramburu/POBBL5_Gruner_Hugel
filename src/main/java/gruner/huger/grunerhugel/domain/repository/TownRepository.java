package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gruner.huger.grunerhugel.model.Town;

public interface TownRepository extends JpaRepository<Town,Integer>{
    
}
