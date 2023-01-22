package gruner.huger.grunerhugel.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gruner.huger.grunerhugel.GrunerhugelApplication;
import gruner.huger.grunerhugel.config.URI;
import gruner.huger.grunerhugel.domain.repository.FarmRepository;
import gruner.huger.grunerhugel.domain.repository.SimulationRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.Farm;
import gruner.huger.grunerhugel.model.Simulation;
import gruner.huger.grunerhugel.model.User;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SimulationRepository simulationRepository;
    @Autowired
    private FarmRepository farmRepository;

    @GetMapping(value = "/login")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping(value = "/")
    public String home(Model model, Authentication authentication) {
        String url = URI.LOGIN.getPath();

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            url = URI.HOME_ADMIN.getPath();
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            try {
                Farm farm = farmRepository.findByUser(user);
                if (farm == null) {
                    throw new NullPointerException();
                }
                url = URI.HOME_USER_FARM.getPath();
            } catch (NullPointerException e) {
                GrunerhugelApplication.logger.info("No farm found for user " + username);
                url = URI.HOME_TUTORIAL.getPath();
            }
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("INVESTOR"))) {
            url = URI.HOME_INVESTOR.getPath();
        }
        return "redirect:" + url;
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
            GrunerhugelApplication.logger.info("No simulations found");
        }
        model.addAttribute("simulations", list);
        return URI.HOME_INVESTOR.getView();
    }

    @PostMapping(value = "/accessDenied/{responsePath}")
    public String accessDenied(@PathVariable String path, Model model) {
        model.addAttribute("accessDenied", true);
        return "redirect:" + path;
    }

    @GetMapping(value = "/tutorial")
    public String tutorial() {
        return URI.HOME_TUTORIAL.getView();
    }
}
