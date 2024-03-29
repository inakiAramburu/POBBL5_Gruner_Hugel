package gruner.huger.grunerhugel.controller;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.config.URI;
import gruner.huger.grunerhugel.domain.repository.FarmHarvesterRepository;
import gruner.huger.grunerhugel.domain.repository.FarmPlowRepository;
import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.FarmSeederRepository;
import gruner.huger.grunerhugel.domain.repository.FarmTractorRepository;
import gruner.huger.grunerhugel.domain.repository.FuelRepository;
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
import gruner.huger.grunerhugel.domain.repository.WeatherRepository;
import gruner.huger.grunerhugel.domain.repository.WheatPriceRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.FarmHarvester;
import gruner.huger.grunerhugel.model.FarmPlow;
import gruner.huger.grunerhugel.model.FarmSeeder;
import gruner.huger.grunerhugel.model.FarmTractor;
import gruner.huger.grunerhugel.model.Harvester;
import gruner.huger.grunerhugel.model.Land;
import gruner.huger.grunerhugel.model.OptimalConditions;
import gruner.huger.grunerhugel.model.Plant;
import gruner.huger.grunerhugel.model.Plow;
import gruner.huger.grunerhugel.model.Seeder;
import gruner.huger.grunerhugel.model.Simulation;
import gruner.huger.grunerhugel.model.Town;
import gruner.huger.grunerhugel.model.Tractor;
import gruner.huger.grunerhugel.model.User;
import gruner.huger.grunerhugel.model.formobjects.CreateLand;
import gruner.huger.grunerhugel.model.formobjects.CreateSimulation;
import gruner.huger.grunerhugel.model.formobjects.EditSimulation;
import gruner.huger.grunerhugel.simulation.Message;
import gruner.huger.grunerhugel.simulation.SimulationProcesses;
import gruner.huger.grunerhugel.simulation.thread.Balance;
import gruner.huger.grunerhugel.simulation.thread.TimeThread;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SimulationController {

    static final String REDIRECT = "redirect:";

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
    private SimulationRepository simulationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private FuelRepository fuelRepository;
    @Autowired
    private WheatPriceRepository wheatPriceRepository;

    SimulationProcesses sim;

    @GetMapping(value = "/main")
    public String main(Model model, HttpSession session) {

        // Set data
        model.addAttribute("tractors", tractorRepository.findAll());
        model.addAttribute("harvesters", harvesterRespository.findAll());
        model.addAttribute("plows", plowRepository.findAll());
        model.addAttribute("seeders", seederRepository.findAll());
        model.addAttribute("crops", plantTypeRepository.findAll());

        // Set farm
        model.addAttribute("simulationCreate", new CreateSimulation());

        // Set land
        model.addAttribute("createLand", new CreateLand());

        return URI.HOME_USER_NO_FARM.getView();
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
        model.addAttribute("lands", landRepository.findByFarm(farm));
        model.addAttribute("tractors", farmTractorRepository.findByFarm(farm));
        model.addAttribute("harvesters", farmHarvesterRepository.findByFarm(farm));
        model.addAttribute("plows", farmPlowRepository.findByFarm(farm));
        model.addAttribute("seeders", farmSeederRepository.findByFarm(farm));
        model.addAttribute("crops", plantTypeRepository.findAll());

        // Get data
        model.addAttribute("simulationEdit", new EditSimulation());

        // Set land
        model.addAttribute("createLand", new CreateLand());

        sim = new SimulationProcesses(farm, weatherRepository, plantRepository, plantTypeRepository, landRepository,
                fuelRepository, wheatPriceRepository);
        sim.constructVehicleRepositories(farmTractorRepository);
        sim.initialize(farm.getMoney(), simulation.getStartDate(),
                simulation.getEndDate(), farmRepository,
                simulationRepository);
        return URI.HOME_USER_FARM.getView();
    }

    @GetMapping(value = "/event-total")
    public String getTotalCost(ModelMap model) {
        int total = 0;

        model.addAttribute("total", total);
        return "simulation/simulation-form :: #totalCost";
    }

    @PostMapping(value = "/addSimulation")
    public String addSimulation(@ModelAttribute("simulationCreate") CreateSimulation newSimulation,
            Model model) {

        // Check if start date is before end date
        if (!newSimulation.getStartDate().before(newSimulation.getEndDate())) {
            model.addAttribute("error", true);
            GrunerhugelApplication.logger.log(Level.WARNING, "Simulation start date cant be before end date");
        } else {
            // Save farm (One to One : User)
            User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            Farm farm = new Farm(user, newSimulation.getBudget(), newSimulation.getNumWorkers());
            farm = farmRepository.save(farm);

            // Save simulation (One to One: Farm)
            Simulation simulation = new Simulation(newSimulation.getStartDate(), newSimulation.getEndDate(), farm);
            simulationRepository.save(simulation);

            // Save tools (Many to Many: Tool + Farm + Quantity)
            if (newSimulation.getNumTractor() != 0) {
                Tractor tractor = tractorRepository.findByName(newSimulation.getTractorName());
                FarmTractor farmTractor = new FarmTractor(farm, tractor, newSimulation.getNumTractor());
                farmTractorRepository.save(farmTractor);
            }

            if (newSimulation.getNumHarvester() != 0) {
                Harvester harvester = harvesterRespository.findByName(newSimulation.getHarvesterName());
                FarmHarvester farmHarvester = new FarmHarvester(farm, harvester, newSimulation.getNumHarvester());
                farmHarvesterRepository.save(farmHarvester);
            }

            if (newSimulation.getNumPlow() != 0) {
                Plow plow = plowRepository.findByName(newSimulation.getPlowName());
                FarmPlow farmPlow = new FarmPlow(farm, plow, newSimulation.getNumPlow());
                farmPlowRepository.save(farmPlow);
            }

            if (newSimulation.getNumSeeder() != 0) {
                Seeder seeder = seederRepository.findByName(newSimulation.getSeederName());
                FarmSeeder farmSeeder = new FarmSeeder(farm, seeder, newSimulation.getNumSeeder());
                farmSeederRepository.save(farmSeeder);
            }

            GrunerhugelApplication.logger.log(Level.INFO, "Farm/Simulation information saved succesfully");
        }

        return REDIRECT + URI.HOME_USER_NO_FARM.getPath();
    }

    @GetMapping(value = "/deleteSimulation")
    public String deleteSimulation(Model model, Authentication authentication) {
        // Get data
        User user = userRepository.findByUsername(authentication.getName());
        Farm farm = farmRepository.findByUser(user);
        Simulation simulation = simulationRepository.findByFarm(farm);

        // Delete data
        farmTractorRepository.findByFarm(farm)
                .forEach(farmTractor -> farmTractorRepository.delete(farmTractor));
        farmHarvesterRepository.findByFarm(farm)
                .forEach(farmHarvester -> farmHarvesterRepository.delete(farmHarvester));
        farmPlowRepository.findByFarm(farm)
                .forEach(farmPlow -> farmPlowRepository.delete(farmPlow));
        farmSeederRepository.findByFarm(farm)
                .forEach(farmSeeder -> farmSeederRepository.delete(farmSeeder));
        landRepository.findByFarm(farm)
                .forEach(land -> {
                    plantRepository.findByLand(land).forEach(plant -> plantRepository.delete(plant));
                    landRepository.delete(land);
                });

        simulationRepository.delete(simulation);
        farmRepository.delete(farm);

        return REDIRECT + URI.HOME_USER_NO_FARM.getPath();
    }

    @PostMapping(value = "/updateSimulation")
    public String updateSimulation(@ModelAttribute("simulation-edit") EditSimulation newSimulation) {

        // Get data
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Farm oldFarm = farmRepository.findByUser(user);
        Simulation oldSimulation = simulationRepository.findByFarm(oldFarm);
        Iterable<FarmHarvester> oldFarmHarvesters = farmHarvesterRepository.findByFarm(oldFarm);
        Iterable<FarmPlow> oldFarmPlows = farmPlowRepository.findByFarm(oldFarm);
        Iterable<FarmSeeder> oldFarmSeeders = farmSeederRepository.findByFarm(oldFarm);
        Iterable<FarmTractor> oldFarmTractors = farmTractorRepository.findByFarm(oldFarm);

        // Edit data
        try {

            // Simulation
            if (!oldSimulation.getEndDate().equals(newSimulation.getEndDate())) {
                if (oldSimulation.getEndDate().before(newSimulation.getEndDate())) {
                    oldSimulation.setEndDate(newSimulation.getEndDate());
                } else {
                    GrunerhugelApplication.logger.info("Start date must be before end date");
                    return REDIRECT + "/simulation";
                }
            }

            // Tools
            for (FarmTractor oldFarmTractor : oldFarmTractors) {
                if (oldFarmTractor.getQuantity() != newSimulation.getNumTractor()) {
                    oldFarmTractor.setQuantity(newSimulation.getNumTractor());
                }
                farmTractorRepository.save(oldFarmTractor);
            }

            for (FarmHarvester oldFarmHarvester : oldFarmHarvesters) {
                if (oldFarmHarvester.getQuantity() != newSimulation.getNumHarvester()) {
                    oldFarmHarvester.setQuantity(newSimulation.getNumHarvester());
                }
                farmHarvesterRepository.save(oldFarmHarvester);
            }

            for (FarmPlow oldFarmPlow : oldFarmPlows) {
                if (oldFarmPlow.getQuantity() != newSimulation.getNumPlow()) {
                    oldFarmPlow.setQuantity(newSimulation.getNumPlow());
                }
                farmPlowRepository.save(oldFarmPlow);
            }

            for (FarmSeeder oldFarmSeeder : oldFarmSeeders) {
                if (oldFarmSeeder.getQuantity() != newSimulation.getNumSeeder()) {
                    oldFarmSeeder.setQuantity(newSimulation.getNumSeeder());
                }
                farmSeederRepository.save(oldFarmSeeder);
            }

        } catch (Exception e) {
            GrunerhugelApplication.logger.info("Error updating the Simulation");
        }

        return REDIRECT + URI.HOME_USER_FARM.getPath();
    }

    @PostMapping(value = "/addLand")
    public String addLand(@ModelAttribute("createLand") CreateLand newLand, HttpServletRequest request) {
        // Save land (One to One: Land + Farm)
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Farm farm = farmRepository.findByUser(user);

        // Normalize town name
        String townName = Normalizer.normalize(newLand.getTown(), Normalizer.Form.NFD);
        townName = townName.replaceAll("[^\\p{ASCII}]", "");
        if (townName.contains("/")) {
            String[] townNames = townName.split("/");
            townName = townNames[0];
        }

        try {
            Town town = townRepository.findByName(townName);
            Land land = new Land(newLand.getSize(), farm, town, newLand.getLatitude(), newLand.getLongitude());
            land = landRepository.save(land);

            // Save plant (One to One: Land + PlantType)
            OptimalConditions plantType = plantTypeRepository.findByName(newLand.getPlantName());
            Plant plant = new Plant(plantType, land);
            plantRepository.save(plant);
        } catch (Exception e) {
            GrunerhugelApplication.logger.log(Level.INFO, "Town not found");
        }

        return REDIRECT + request.getContextPath();
    }

    @GetMapping(value = "/deleteLand/{id}")
    public String deleteLand(@PathVariable int id) {
        // Delete plant
        Optional<Land> land = landRepository.findById(id);
        if (land.isPresent()) {
            plantRepository.findByLand(land.get())
                    .forEach(p -> plantRepository.delete(p));
            landRepository.delete(land.get());
        }

        return REDIRECT + URI.HOME_USER_FARM.getPath();
    }

    @GetMapping(value = "/startSimulation")
    @ResponseBody
    public String startSimulation() {
        sim.start();
        return "hi";
    }

    @GetMapping(value = "/pauseSimulation")
    @ResponseBody
    public String pauseSimulation() {
        TimeThread.pause();
        return "hi";
    }

    @GetMapping(value = "/changeAcceleration")
    @ResponseBody
    public int changeAcceleration(int value) {

        int newValue;

        switch (value) {
            case 1:
                newValue = 2;
                break;
            case 2:
                newValue = 4;
                break;
            case 4:
                newValue = 1;
                break;
            default:
                newValue = 1;
                break;
        }

        TimeThread.setAccelerator(newValue);

        return newValue;
    }

    @GetMapping(value = "/changeBalance")
    @ResponseBody
    public int changeBalance() {

        return Balance.getBalance();
    }

    @GetMapping(value = "/changeLandTable")
    public String changeOperationTable(ModelMap model) {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Farm farm = farmRepository.findByUser(user);
        model.addAttribute("lands", landRepository.findByFarm(farm));
        return "simulation/simulation :: #lands-list";
    }

    @GetMapping(value = "/changeOperationTable")
    public String changeLandTable(ModelMap model) {
        List<Message> lista = Balance.getlMessages();
        model.addAttribute("operations", lista);
        return "simulation/simulation :: #operations-list";
    }
}
