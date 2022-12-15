package gruner.huger.grunerhugel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gruner.huger.grunerhugel.domain.repository.SimulationRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.User;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;
    private SimulationRepository simulationRepository;
    
    @GetMapping(value = {"/", "login"})
    public String index() {
        return "login";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user/user-form";
    }

    @GetMapping(value = "/admin")
    public String admin(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }

    @GetMapping(value = "/investor")
    public String investor(Model model) {
        model.addAttribute("simulations", simulationRepository.findAll());
        return "investor";
    }
}
