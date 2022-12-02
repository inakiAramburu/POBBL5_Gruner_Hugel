package gruner.huger.grunerhugel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import gruner.huger.grunerhugel.domain.user.UserRepository;
import gruner.huger.grunerhugel.model.Role;
import gruner.huger.grunerhugel.model.User;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {

        System.out.println(user);
        user.setRole(new Role("USER"));
        userRepository.save(user);

        return "redirect:/login"; //A donde va?
    }
}
