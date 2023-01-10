package gruner.huger.grunerhugel.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.SimulationRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.Simulation;
import gruner.huger.grunerhugel.model.User;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;
    private SimulationRepository simulationRepository;
    private FarmRepository farmRepository;

    @GetMapping(value = "/login")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping(value = "/")
    public String home(Model model, Authentication authentication) {
        String url = "redirect:/login";

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            url = "redirect:/admin";
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            try {
                farmRepository.findByUser(user);
                url = "redirect:/simulation";
            } catch (Exception e) {
                System.out.println("No simulations found");
                url = "redirect:/main";
            }
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("INVESTOR"))) {
            url = "redirect:/investor";
        }
        return url;
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user/user-form";
    }

    @GetMapping(value = "/admin")
    public String admin(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/user-list";
    }

    @GetMapping(value = "/investor")
    public String investor(Model model) {
        Iterable<Simulation> list = null;
        try {
            list = simulationRepository.findAll();
        } catch (NullPointerException e) {
            System.out.println("No simulations found");
        }
        model.addAttribute("simulations", list);
        return "investor";
    }

    @PostMapping(value = "/accessDenied/{responsePath}")
    public String accessDenied(@PathVariable String path, Model model) {
        model.addAttribute("accessDenied", true);
        return "redirect:" + path;
    }
}
