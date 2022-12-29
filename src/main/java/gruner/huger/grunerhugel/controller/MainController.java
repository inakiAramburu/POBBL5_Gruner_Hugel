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
        model.addAttribute("land", new Land());
        model.addAttribute("simulation", new Simulation());
        model.addAttribute("newTractor", new Tractor());
        model.addAttribute("newHarvester", new Harvester());
        model.addAttribute("newPlow", new Plow());
        model.addAttribute("newSeeder", new Seeder());
        model.addAttribute("farmTractor", new FarmTractor());
        model.addAttribute("farmHarvester", new FarmHarvester());
        model.addAttribute("farmPlow", new FarmPlow());
        model.addAttribute("farmSeeder", new FarmSeeder());
        model.addAttribute("numTractors", new String());
        model.addAttribute("numHarvesters", (int) 0);
        return "main";
    }

    @PostMapping(value = "/simulation")
    public String simulation(@ModelAttribute("simulation") Simulation simulation, @ModelAttribute("farm") Farm farm,
            @ModelAttribute("newTractor") Tractor tractor, @ModelAttribute("newHarvester") Harvester harvester,
            @ModelAttribute("newSeeder") Seeder seeder, @ModelAttribute("newPlow") Plow plow,
            @ModelAttribute(value = "numTractors") String numTractors,
            @ModelAttribute(value = "numHarvesters") int numHarvesters) {
        GrunerhugelApplication.logger.log(Level.INFO, "Simulation started");

        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        farm.setUser(user);
        farm = farmRepository.save(farm);

        simulation.setFarm(farm);
        simulationRepository.save(simulation); 
        
        System.out.println(numTractors);
        System.out.println(numHarvesters);
        tractor = tractorRepository.findbyName(tractor.getTractorName());
        FarmTractor farmTractor = new FarmTractor(farm, tractor, Integer.parseInt(numTractors));
        farmTractorRepository.save(farmTractor);

        harvester = harvesterRespository.findbyName(harvester.getHarvesterName());
        FarmHarvester farmHarvester = new FarmHarvester(farm, harvester, numHarvesters);
        farmHarvesterRepository.save(farmHarvester);

        /*plow = plowRepository.findbyName(plow.getPlowName());
        FarmPlow farmPlow = new FarmPlow(farm, plow, numPlows);
        farmPlowRepository.save(farmPlow);

        seeder = seederRepository.findbyName(seeder.getSeederName());
        FarmSeeder farmSeeder = new FarmSeeder(farm, seeder, numSeeders);
        farmSeederRepository.save(farmSeeder);

       

        for (Integer i = 0; i < numWorkers; i++) {
            Worker worker = new Worker();
            worker.setFarm(farm);
            workerRepository.save(worker);
        }*/

        return "simulation";
    }
}
