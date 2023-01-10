package gruner.huger.grunerhugel.controller;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Plow;
import gruner.huger.grunerhugel.model.Seeder;
import gruner.huger.grunerhugel.model.Simulation;
import gruner.huger.grunerhugel.model.Town;
import gruner.huger.grunerhugel.model.Tractor;
import gruner.huger.grunerhugel.model.User;
import gruner.huger.grunerhugel.model.Worker;
import jakarta.servlet.http.HttpSession;

@Controller
public class SimulationController {

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
    public String main(Model model, HttpSession session) {
        // Set atributtes
        String formId = "simulationCreate";
        int total = 10000;

        session.setAttribute("formId", formId);
        session.setAttribute("total", total);

        // Set data
        model.addAttribute("tractors", tractorRepository.findAll());
        model.addAttribute("harvesters", harvesterRespository.findAll());
        model.addAttribute("plows", plowRepository.findAll());
        model.addAttribute("seeders", seederRepository.findAll());

        // Set farm
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

        // Set land
        model.addAttribute("land", new Land());
        model.addAttribute("plant", new Plant());
        model.addAttribute("town", new Town());

        return "simulation/simulation-form";
    }

    @GetMapping(value = "/simulation")
    public String simulation(Model model, Authentication authentication) {
        // Get data
        User user = userRepository.findByUsername(authentication.getName());
        Farm farm = farmRepository.findByUser(user);
        Simulation simulation = simulationRepository.findByFarm(farm);

        // Set data
        model.addAttribute("simulation", simulation);
        model.addAttribute("farm", farm);
        model.addAttribute("tractors", farmTractorRepository.findByFarm(farm));
        model.addAttribute("harvesters", farmHarvesterRepository.findByFarm(farm));
        model.addAttribute("plows", farmPlowRepository.findByFarm(farm));
        model.addAttribute("seeders", farmSeederRepository.findByFarm(farm));

        // Get data
        model.addAttribute("newFarm", new Farm());
        model.addAttribute("newSimulation", new Simulation());
        model.addAttribute("newTractor", new Tractor());
        model.addAttribute("newHarvester", new Harvester());
        model.addAttribute("newPlow", new Plow());
        model.addAttribute("newSeeder", new Seeder());
        model.addAttribute("newFarmTractor", new FarmTractor());
        model.addAttribute("newFarmHarvester", new FarmHarvester());
        model.addAttribute("newFarmPlow", new FarmPlow());
        model.addAttribute("newFarmSeeder", new FarmSeeder());

        return "simulation/simulation";
    }

    @PostMapping(value = "/addSimulation")
    public String simulation(@ModelAttribute("newSimulation") Simulation simulation,
            @ModelAttribute("newFarm") Farm farm,
            @ModelAttribute("newTractor") Tractor tractor, @ModelAttribute("newHarvester") Harvester harvester,
            @ModelAttribute("newSeeder") Seeder seeder, @ModelAttribute("newPlow") Plow plow,
            @ModelAttribute("farmTractor") FarmTractor farmTractor,
            @ModelAttribute("farmHarvester") FarmHarvester farmHarvester,
            @ModelAttribute("farmPlow") FarmPlow farmPlow, @ModelAttribute("farmSeeder") FarmSeeder farmSeeder,
            Model model) {

        String path = "redirect:/simulation";

        // Check if start date is before end date
        if (!simulation.getStartDate().before(simulation.getEndDate())) {
            model.addAttribute("error", "Start date must be before end date");
            path = "redirect:/main";
        } else {
            // Save farm (One to One : User)
            User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            farm.setUser(user);
            farm.setFuel(0);
            farm = farmRepository.save(farm);

            // Save simulation (One to One: Farm)
            simulation.setFarm(farm);
            simulationRepository.save(simulation);

            // Save tools (Many to Many: Tool + Farm + Quantity)
            System.out.println("Farm Tractor num:" + farmTractor.getQuantity());
            if (farmTractor.getQuantity() != 0) {
                tractor = tractorRepository.findByName(tractor.getTractorName());
                farmTractor = new FarmTractor(farm, tractor, farmTractor.getQuantity());
                farmTractorRepository.save(farmTractor);
            }

            System.out.println("Farm Harvester num:" + farmHarvester.getQuantity());
            if (farmHarvester.getQuantity() != 0) {
                harvester = harvesterRespository.findByName(harvester.getHarvesterName());
                farmHarvester = new FarmHarvester(farm, harvester, farmHarvester.getQuantity());
                farmHarvesterRepository.save(farmHarvester);
            }

            System.out.println("Farm Plow num:" + farmPlow.getQuantity());
            if (farmPlow.getQuantity() != 0) {
                plow = plowRepository.findByName(plow.getPlowName());
                farmPlow = new FarmPlow(farm, plow, farmPlow.getQuantity());
                farmPlowRepository.save(farmPlow);
            }

            System.out.println("Farm Seeder num:" + farmSeeder.getQuantity());
            if (farmSeeder.getQuantity() != 0) {
                seeder = seederRepository.findByName(seeder.getSeederName());
                farmSeeder = new FarmSeeder(farm, seeder, farmSeeder.getQuantity());
                farmSeederRepository.save(farmSeeder);
            }

            // Save workers (One to One: Farm)
            for (Integer i = 0; i < farm.getNumWorkers(); i++) {
                Worker worker = new Worker();
                worker.setFarm(farm);
                workerRepository.save(worker);
            }

            GrunerhugelApplication.logger.log(Level.INFO, "Farm/Simulation information saved succesfully");
        }

        return path;
    }

    @PostMapping(value = "/addLand")
    public String addLand(@ModelAttribute("land") Land land, @ModelAttribute("town") Town town,
            @ModelAttribute("plant") Plant plant) {
        // Save town (One to One: Land + Farm)
        town = townRepository.findByName(town.getName());
        land.setTown(town);
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Farm farm = farmRepository.findByUser(user);
        land.setFarm(farm);
        land = landRepository.save(land);

        // Save plant (One to One: Land + PlantType)
        plant.setLand(land);
        plant.setOptimalConditions(plantTypeRepository.findByName(plant.getName()));
        plantRepository.save(plant);

        return "simulation";
    }

    @GetMapping(value = "/deleteSimulation")
    public String deleteSimulation(Model model, Authentication authentication) {
        // Get data
        User user = userRepository.findByUsername(authentication.getName());
        Farm farm = farmRepository.findByUser(user);
        Simulation simulation = simulationRepository.findByFarm(farm);

        // Delete data
        farmTractorRepository.findByFarm(farm).forEach((FarmTractor) -> {
            farmTractorRepository.delete(FarmTractor);
        });
        farmHarvesterRepository.findByFarm(farm).forEach((FarmHarvester) -> {
            farmHarvesterRepository.delete(FarmHarvester);
        });
        farmPlowRepository.findByFarm(farm).forEach((FarmPlow) -> {
            farmPlowRepository.delete(FarmPlow);
        });
        farmSeederRepository.findByFarm(farm).forEach((FarmSeeder) -> {
            farmSeederRepository.delete(FarmSeeder);
        });

        workerRepository.findByFarm(farm).forEach((Worker) -> {
            workerRepository.delete(Worker);
        });

        simulationRepository.delete(simulation);
        farmRepository.delete(farm);

        return "redirect:/main";
    }

    @PostMapping(value = "/updateSimulation")
    public String updateSimulation(@ModelAttribute("farm") Farm farm,
            @ModelAttribute("simulation") Simulation simulation, @ModelAttribute("tractor") Tractor tractor,
            @ModelAttribute("harvester") Harvester harvester, @ModelAttribute("plow") Plow plow,
            @ModelAttribute("seeder") Seeder seeder, @ModelAttribute("farmTractor") FarmTractor farmTractor,
            @ModelAttribute("farmHarvester") FarmHarvester farmHarvester, @ModelAttribute("farmPlow") FarmPlow farmPlow,
            @ModelAttribute("farmSeeder") FarmSeeder farmSeeder, Authentication authentication) {
        // Get data
        User user = userRepository.findByUsername(authentication.getName());
        Farm oldFarm = farmRepository.findByUser(user);
        Simulation oldSimulation = simulationRepository.findByFarm(farm);
        Iterable<FarmHarvester> oldFarmHarvesters = farmHarvesterRepository.findByFarm(oldFarm);
        Iterable<FarmPlow> oldFarmPlows = farmPlowRepository.findByFarm(oldFarm);
        Iterable<FarmSeeder> oldFarmSeeders = farmSeederRepository.findByFarm(oldFarm);
        Iterable<FarmTractor> oldFarmTractors = farmTractorRepository.findByFarm(oldFarm);

        return "simulation/simulation";
    }
}
