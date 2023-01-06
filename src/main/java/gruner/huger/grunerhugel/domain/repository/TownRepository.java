package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.Town;

@Repository
public interface TownRepository extends JpaRepository<Town,Integer>{
    
}
