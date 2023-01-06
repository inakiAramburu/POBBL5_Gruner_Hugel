package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gruner.huger.grunerhugel.model.Worker;

@Repository
public interface WorkerRepository extends CrudRepository<Worker,Integer>{
    
}
