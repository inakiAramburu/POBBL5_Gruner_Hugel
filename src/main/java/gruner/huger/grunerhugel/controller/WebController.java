package gruner.huger.grunerhugel.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import gruner.huger.grunerhugel.domain.user.UserRepository;
import gruner.huger.grunerhugel.model.User;

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
    public String create(Model model) {
        model.addAttribute("user", new User());
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

    @GetMapping("/edit/{id}")
    public String update(@PathVariable int id,Model model) {

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            model.addAttribute("user", user.get());
        }
        return "user/user-edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }
}
