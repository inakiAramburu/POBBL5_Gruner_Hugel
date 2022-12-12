package gruner.huger.grunerhugel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import gruner.huger.grunerhugel.domain.repository.ToolsRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Land;

@Controller
public class MainController {
    
    @Autowired
    private ToolsRepository toolsRepository;

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("tractors", toolsRepository.getToolsByType("TRACTOR"));
        model.addAttribute("harvesters", toolsRepository.getToolsByType("HARVESTER"));
        model.addAttribute("plows", toolsRepository.getToolsByType("PLOW"));
        model.addAttribute("seeds", toolsRepository.getToolsByType("SEED"));
        model.addAttribute("farm",new Farm());
        model.addAttribute("land", new Land());
        return "main";
    }

    @PostMapping("/simulation")
    public String simulation(@ModelAttribute("farm") Farm farm, @ModelAttribute("land") Land land) {
        System.out.println(farm);
        System.out.println(land);
        return "simulation";
    }
}
