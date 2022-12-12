package gruner.huger.grunerhugel.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import gruner.huger.grunerhugel.model.Tools;

public interface ToolsRepository extends CrudRepository<Tools,Integer>{

    List<Tools> getToolsByType(String type);
}
