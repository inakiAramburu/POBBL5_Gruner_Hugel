package gruner.huger.grunerhugel.simulation;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.FarmTractorRepository;
import gruner.huger.grunerhugel.domain.repository.FuelRepository;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.domain.repository.PlantRepository;
import gruner.huger.grunerhugel.domain.repository.SimulationRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.domain.repository.WeatherRepository;
import gruner.huger.grunerhugel.domain.repository.WheatPriceRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Role;
import gruner.huger.grunerhugel.model.Town;
import gruner.huger.grunerhugel.model.User;

@SpringBootTest
public class SimulationProcessesTest {

    
    @Autowired
    FarmRepository farmRepository;
    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    PlantRepository plantRepository;
    @Autowired
    SimulationRepository simulationRepository;
    @Autowired
    OptimalConditionsRepository opCondRepository;
    @Autowired
    LandRepository landRepository;
    @Autowired
    FuelRepository fuelRepository;
    @Autowired
    WheatPriceRepository wPriceRepository;
    @Autowired
    UserRepository uRepository;
    @Autowired
    FarmTractorRepository fTractRepository;
    
    @Test
    public void testThreadInitialization() {
       /* // Arrange
        Date dataStart=new Date();
        Date dataEnd=new Date();
        
        User user = new User();
        user.setUsername("test");
        user.setRole(new Role("ADMIN"));
        Farm farm = new Farm(user, 1, 1);
        
        SimulationProcesses simulation = new SimulationProcesses(farm, weatherRepository, plantRepository, opCondRepository, landRepository, fuelRepository, wPriceRepository);
        Town town =new Town();
        town.setName("Abaigar");
        farm.setUser(user);

        simulation.constructVehicleRepositories(fTractRepository);
       
        farm = farmRepository.save(farm);

        List<Land> lands=new ArrayList();
        Land land =new Land(1.00,farm,town,"25","25");
        lands.add(land);
        farm.setLands(lands);
        System.out.println(farm.getUser());

        
        


        // Act
        dataStart.setTime(0);
        dataEnd.setTime(1000000000);
        simulation.initialize(10000, dataStart, dataEnd, farmRepository, simulationRepository);
        simulation.start();
        
        // Assert
        assertTrue(simulation.wThread.isAlive());
        assertTrue(simulation.pThread.isAlive());
        assertTrue(simulation.tThread.isAlive());
        assertTrue(simulation.fThread.isAlive());
        assertTrue(simulation.wpThread.isAlive());
        assertTrue(simulation.lThread.isAlive());
    }
    
    */
    
    }
        

