package gruner.huger.grunerhugel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import gruner.huger.grunerhugel.domain.user.UserRepository;

@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping(path = {"/","/login"})
    public String index() {
        return "login";
    }

    @GetMapping("/main")
    public String main() {
        
        return "main";
    }

    @GetMapping("/create")
    public String create() {
        return "user/user-form";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }

    @ModelAttribute("currentUsername")
    public String currentUsername(Authentication authentication) {
        if(authentication == null){
            return "";
        }
        return authentication.getName();
    }
}
