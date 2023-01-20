package gruner.huger.grunerhugel.controller;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.config.URI;
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
import gruner.huger.grunerhugel.model.formObjects.CreateSimulation;
import gruner.huger.grunerhugel.model.formObjects.EditSimulation;
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
        int total = 10000;

        session.setAttribute("total", total);

        // Set data
        model.addAttribute("tractors", tractorRepository.findAll());
        model.addAttribute("harvesters", harvesterRespository.findAll());
        model.addAttribute("plows", plowRepository.findAll());
        model.addAttribute("seeders", seederRepository.findAll());

        // Set farm
        model.addAttribute("simulationCreate", new CreateSimulation());

        // Set land
        model.addAttribute("land", new Land());
        model.addAttribute("plant", new Plant());
        // model.addAttribute("town", new Town());

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
        model.addAttribute("tractors", farmTractorRepository.findByFarm(farm));
        model.addAttribute("harvesters", farmHarvesterRepository.findByFarm(farm));
        model.addAttribute("plows", farmPlowRepository.findByFarm(farm));
        model.addAttribute("seeders", farmSeederRepository.findByFarm(farm));

        // Get data
        model.addAttribute("simulationEdit", new EditSimulation());

        return URI.HOME_USER_FARM.getView();
    }

    @GetMapping(value = "/event-total")
    public String getTotalCost(ModelMap model) {
        int total = 0;

        model.addAttribute("total", total);
        return "simulation/simulation-form :: #totalCost";
    }

    @PostMapping(value = "/addSimulation")
    public String addSimulation(@ModelAttribute("simulation-create") CreateSimulation newSimulation,
            Model model) {

        String path = URI.HOME_USER_FARM.getPath();

        // Check if start date is before end date
        if (!newSimulation.getStartDate().before(newSimulation.getEndDate())) {
            model.addAttribute("error", "Start date must be before end date");
            path = URI.HOME_USER_NO_FARM.getPath();
        } else {
            // Save farm (One to One : User)
            User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            Farm farm = new Farm(user, newSimulation.getBudget(), newSimulation.getNumWorkers());
            farm = farmRepository.save(farm);

            // Save workers (One to One: Farm)
            for (Integer i = 0; i < farm.getNumWorkers(); i++) {
                Worker worker = new Worker(farm);
                workerRepository.save(worker);
            }

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

        return "redirect:" + path;
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
        farmTractorRepository.findByFarm(farm)
                .forEach(farmTractor -> farmTractorRepository.delete(farmTractor));
        farmHarvesterRepository.findByFarm(farm)
                .forEach(farmHarvester -> farmHarvesterRepository.delete(farmHarvester));
        farmPlowRepository.findByFarm(farm)
                .forEach(farmPlow -> farmPlowRepository.delete(farmPlow));
        farmSeederRepository.findByFarm(farm)
                .forEach(farmSeeder -> farmSeederRepository.delete(farmSeeder));
        workerRepository.findByFarm(farm)
                .forEach(worker -> workerRepository.delete(worker));

        simulationRepository.delete(simulation);
        farmRepository.delete(farm);

        return "redirect:" + URI.HOME_USER_NO_FARM.getPath();
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
        Iterable<Worker> oldWorkers = workerRepository.findByFarm(oldFarm);

        // Edit data
        try {

            // Simulation
            if (!oldSimulation.getEndDate().equals(newSimulation.getEndDate())) {
                if (oldSimulation.getEndDate().before(newSimulation.getEndDate())) {
                    oldSimulation.setEndDate(newSimulation.getEndDate());
                } else {
                    System.out.println("Start date must be before end date");
                    return "redirect:/simulation"; // aiqu cambiarlo tendria no deberia de reiniciar toda la simulacion
                                                   // xd
                }
            }

            // Tools
            for (FarmTractor oldFarmTractor : oldFarmTractors) {
                /*
                 * if (oldFarmTractor.getTractor().getTractorName().equals(newSimulation.
                 * getTractorName())) {
                 * } else {
                 * oldFarmTractor.getTractor().setTractorName(newSimulation.getTractorName());
                 * }
                 */

                if (oldFarmTractor.getQuantity() != newSimulation.getNumTractor()) {
                    oldFarmTractor.setQuantity(newSimulation.getNumTractor());
                }
                farmTractorRepository.save(oldFarmTractor);
            }

            for (FarmHarvester oldFarmHarvester : oldFarmHarvesters) {
                /*
                 * if (oldFarmHarvester.getHarvester().getHarvesterName().equals(newSimulation.
                 * getHarvesterName())) {
                 * } else {
                 * oldFarmHarvester.getHarvester().setHarvesterName(newSimulation.
                 * getHarvesterName());
                 * }
                 */

                if (oldFarmHarvester.getQuantity() != newSimulation.getNumHarvester()) {
                    oldFarmHarvester.setQuantity(newSimulation.getNumHarvester());
                }
                farmHarvesterRepository.save(oldFarmHarvester);
            }

            for (FarmPlow oldFarmPlow : oldFarmPlows) {
                // if (oldFarmPlow.getPlow().getPlowName().equals(newSimulation.getPlowName()))
                // {
                // } else {
                // oldFarmPlow.getPlow().setPlowName(newSimulation.getPlowName());
                // }

                if (oldFarmPlow.getQuantity() != newSimulation.getNumPlow()) {
                    oldFarmPlow.setQuantity(newSimulation.getNumPlow());
                }
                farmPlowRepository.save(oldFarmPlow);
            }

            for (FarmSeeder oldFarmSeeder : oldFarmSeeders) {
                // if
                // (oldFarmSeeder.getSeeder().getSeederName().equals(newSimulation.getSeederName()))
                // {
                // } else {
                // oldFarmSeeder.getSeeder().setSeederName(newSimulation.getSeederName());
                // }

                if (oldFarmSeeder.getQuantity() != newSimulation.getNumSeeder()) {
                    oldFarmSeeder.setQuantity(newSimulation.getNumSeeder());
                }
                farmSeederRepository.save(oldFarmSeeder);
            }

            // Worker
            if (oldFarm.getNumWorkers() != newSimulation.getNumWorkers()) {

                if (oldFarm.getNumWorkers() > newSimulation.getNumWorkers()) {
                    int numWorkers = oldFarm.getNumWorkers() - newSimulation.getNumWorkers();
                    for (int i = 0; i < numWorkers; i++) {
                        workerRepository.delete(oldWorkers.iterator().next());
                    }
                } else {
                    int numWorkers = newSimulation.getNumWorkers() - oldFarm.getNumWorkers();
                    for (int i = 0; i < numWorkers; i++) {
                        Worker worker = new Worker();
                        worker.setFarm(oldFarm);
                        workerRepository.save(worker);
                    }
                }
                oldFarm.setNumWorkers(newSimulation.getNumWorkers());
                farmRepository.save(oldFarm);
            }

        } catch (Exception e) {
            System.out.println("Error updating the Simulation");
        }

        return "redirect:" + URI.HOME_USER_FARM.getPath();
    }

    @GetMapping(value = "/tutorial")
    public String tutorial(Model model, Authentication authentication) {
        // Get data
        User user = userRepository.findByUsername(authentication.getName());
        Farm farm = farmRepository.findByUser(user);

        return URI.HOME_TUTORIAL.getView();
    }
}
