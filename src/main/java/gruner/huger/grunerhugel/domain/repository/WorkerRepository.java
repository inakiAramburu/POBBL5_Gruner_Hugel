package gruner.huger.grunerhugel.domain.repository;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Worker;

public interface WorkerRepository extends CrudRepository<Worker,Integer>{
    
}
