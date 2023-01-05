package gruner.huger.grunerhugel.controller;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.FarmHarvesterRepository;
import gruner.huger.grunerhugel.domain.repository.FarmPlowRepository;
import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.FarmSeederRepository;
import gruner.huger.grunerhugel.domain.repository.FarmTractorRepository;
import gruner.huger.grunerhugel.domain.repository.HarvesterRespository;
import gruner.huger.grunerhugel.domain.repository.LandRepository;
import gruner.huger.grunerhugel.domain.repository.OptimalConditionsRepository;
import gruner.huger.grunerhugel.domain.repository.PlantRepository;
import gruner.huger.grunerhugel.domain.repository.PlowRepository;
import gruner.huger.grunerhugel.domain.repository.SeederRepository;
import gruner.huger.grunerhugel.domain.repository.SimulationRepository;
import gruner.huger.grunerhugel.domain.repository.TownRepository;
import gruner.huger.grunerhugel.domain.repository.TractorRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.domain.repository.WorkerRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.FarmPlow;
import gruner.huger.grunerhugel.model.FarmSeeder;
import gruner.huger.grunerhugel.model.FarmTractor;
import gruner.huger.grunerhugel.model.Harvester;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.Plow;
import gruner.huger.grunerhugel.model.Seeder;
import gruner.huger.grunerhugel.model.Simulation;
import gruner.huger.grunerhugel.model.Tractor;
import gruner.huger.grunerhugel.model.User;
import gruner.huger.grunerhugel.model.Worker;

@Controller
public class MainController {

    @Autowired
    private FarmRepository farmRepository;
    @Autowired
    private FarmTractorRepository farmTractorRepository;
    @Autowired
    private FarmHarvesterRepository farmHarvesterRepository;
    @Autowired
    private FarmPlowRepository farmPlowRepository;
    @Autowired
    private FarmSeederRepository farmSeederRepository;
    @Autowired
    private HarvesterRespository harvesterRespository;
    @Autowired
    private LandRepository landRepository;
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private OptimalConditionsRepository plantTypeRepository;
    @Autowired
    private PlowRepository plowRepository;
    @Autowired
    private SeederRepository seederRepository;
    @Autowired
    private TownRepository townRepository;
    @Autowired
    private TractorRepository tractorRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private SimulationRepository simulationRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/main")
    public String main(Model model) {
        model.addAttribute("tractors", tractorRepository.findAll());
        model.addAttribute("harvesters", harvesterRespository.findAll());
        model.addAttribute("plows", plowRepository.findAll());
        model.addAttribute("seeders", seederRepository.findAll());
        model.addAttribute("farm", new Farm());
        model.addAttribute("simulation", new Simulation());
        model.addAttribute("newTractor", new Tractor());
        model.addAttribute("newHarvester", new Harvester());
        model.addAttribute("newPlow", new Plow());
        model.addAttribute("newSeeder", new Seeder());
        model.addAttribute("farmTractor", new FarmTractor());
        model.addAttribute("farmHarvester", new FarmHarvester());
        model.addAttribute("farmPlow", new FarmPlow());
        model.addAttribute("farmSeeder", new FarmSeeder());
        return "main";
    }

    @PostMapping(value = "/simulation")
    public String simulation(@ModelAttribute("simulation") Simulation simulation, @ModelAttribute("farm") Farm farm,
            @ModelAttribute("newTractor") Tractor tractor, @ModelAttribute("newHarvester") Harvester harvester,
            @ModelAttribute("newSeeder") Seeder seeder, @ModelAttribute("newPlow") Plow plow,
            @ModelAttribute("farmTractor") FarmTractor farmTractor, @ModelAttribute("farmHarvester") FarmHarvester farmHarvester,
            @ModelAttribute("farmPlow") FarmPlow farmPlow, @ModelAttribute("farmSeeder") FarmSeeder farmSeeder) {
        GrunerhugelApplication.logger.log(Level.INFO, "Simulation started");

        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        farm.setUser(user);
        farm.setFuel(0);
        farm = farmRepository.save(farm);

        simulation.setFarm(farm);
        simulationRepository.save(simulation); 
        
        tractor = tractorRepository.findbyName(tractor.getTractorName());
        farmTractor = new FarmTractor(farm, tractor, farmTractor.getQuantity());
        farmTractorRepository.save(farmTractor);

        harvester = harvesterRespository.findbyName(harvester.getHarvesterName());
        farmHarvester = new FarmHarvester(farm, harvester, farmHarvester.getQuantity());
        farmHarvesterRepository.save(farmHarvester);

        plow = plowRepository.findbyName(plow.getPlowName());
        farmPlow = new FarmPlow(farm, plow, farmPlow.getQuantity());
        farmPlowRepository.save(farmPlow);

        seeder = seederRepository.findbyName(seeder.getSeederName());
        farmSeeder = new FarmSeeder(farm, seeder, farmSeeder.getQuantity());
        farmSeederRepository.save(farmSeeder);

        for (Integer i = 0; i < farm.getNumWorkers(); i++) {
            Worker worker = new Worker();
            worker.setFarm(farm);
            workerRepository.save(worker);
        }

        return "simulation";
    }
}
