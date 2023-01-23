package gruner.huger.grunerhugel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.FuelRepository;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.domain.repository.PlantRepository;
import gruner.huger.grunerhugel.domain.repository.SimulationRepository;
import gruner.huger.grunerhugel.domain.repository.WeatherRepository;
import gruner.huger.grunerhugel.domain.repository.WheatPriceRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Simulation;
import gruner.huger.grunerhugel.simulation.SimulationProcesses;

@SpringBootTest
class ClockTests {
	SimulationProcesses sim;

	@Autowired
	PlantRepository pRepository;
	@Autowired
	WeatherRepository wRepository;
	@Autowired
	FarmRepository farmRepository;
	@Autowired
	OptimalConditionsRepository oConditions;
	@Autowired
	LandRepository lRepository;
	@Autowired
	FuelRepository fRepository;
	@Autowired
	WheatPriceRepository wPriceRepository;
	@Autowired
	SimulationRepository simulationRepository;

	Farm farm;
	Simulation simulation;

	// @Test
	// @Timeout(value = 5000)
	// void test() throws InterruptedException {
	// farm = farmRepository.findById(6).get();
	// simulation = simulationRepository.findByFarm(farm);
	// sim = new SimulationProcesses(farm, wRepository, pRepository, oConditions,
	// lRepository, fRepository,
	// wPriceRepository);
	// sim.initialize(farm.getMoney(), simulation.getStartDate(),
	// simulation.getEndDate(), farmRepository,
	// simulationRepository);
	// sim.run();
	// }

	// @Test
	// @Timeout(value = 10000)
	// void test2() throws InterruptedException {
	// farm = farmRepository.findById(6).get();
	// sim = new SimulationProcesses(farm, wRepository, pRepository, oConditions,
	// lRepository, fRepository,
	// wPriceRepository);
	// TimeThread.setAccelerator(2);
	// sim.run();
	// }

}
