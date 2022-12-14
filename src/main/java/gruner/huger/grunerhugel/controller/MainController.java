package gruner.huger.grunerhugel.controller;

import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.domain.repository.ToolsRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Land;

@Controller
public class MainController {
    
    @Autowired 
    private ToolsRepository toolsRepository;

    @GetMapping(value = "/main")
    public String main(Model model) {
        model.addAttribute("tractors", toolsRepository.getToolsByType("TRACTOR"));
        model.addAttribute("harvesters", toolsRepository.getToolsByType("HARVESTER"));
        model.addAttribute("plows", toolsRepository.getToolsByType("PLOW"));
        model.addAttribute("seeds", toolsRepository.getToolsByType("SEED"));
        model.addAttribute("farm",new Farm());
        model.addAttribute("land", new Land());
        return "main";
    }

    @PostMapping(value = "/simulation")
    public String simulation(@ModelAttribute("farm") Farm farm, @ModelAttribute("land") Land land) {
        GrunerhugelApplication.logger.log(Level.SEVERE,"farm: {0}.",farm);
        GrunerhugelApplication.logger.log(Level.SEVERE,"land: {0}.",land);
        return "simulation";
    }
}
