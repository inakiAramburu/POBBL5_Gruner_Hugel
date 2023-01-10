package gruner.huger.grunerhugel.RepositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.domain.repository.HarvesterRespository;
import gruner.huger.grunerhugel.model.Harvester;

@SpringBootTest

public class HarvesterRespositoryTest {
   
    
    @Autowired
    private HarvesterRespository harvesterRespository;
   
     @Test
    public void testFindByName() {
        Harvester harvester = getHarvester();	     
        harvesterRespository.save(harvester);
        Harvester result = harvesterRespository.findById(harvester.getHarvesterName()).get();
        System.out.println("harvester: "+harvester.getHarvesterName());
        System.out.println("result: "+result.getHarvesterName());
        assertEquals(harvester.getHarvesterName(), result.getHarvesterName());	 	       
        System.out.println("hola");
    } 

    @AfterEach
    @Test
    public void testDeleteById() {
        Harvester harvester = getHarvester();
        harvesterRespository.delete(harvester);
        List<Harvester> result = new ArrayList<>();
        harvesterRespository.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), 10);
    }


    private Harvester getHarvester() {
        Harvester harvester = new Harvester();
        harvester.setHarvesterName("prueba2");
        harvester.setPrice(100);
        harvester.setMaintenance(100);
        harvester.setPower(100);
        harvester.setMaxSpeed(100);
        harvester.setFuelCapacity(100);
        harvester.setCultivationCapacity(100);
        return harvester;
    }

}