package gruner.huger.grunerhugel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.Role;
import gruner.huger.grunerhugel.model.User;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/register")
    public String register(@ModelAttribute User user, Model model) {
        String password = user.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        if(user.getRole() == null){
            user.setRole(new Role("USER"));
        }
        userRepository.save(user);
        return "login";
    }

    @GetMapping(value = "/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
