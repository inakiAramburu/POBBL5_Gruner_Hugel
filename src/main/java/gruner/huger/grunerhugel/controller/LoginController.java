package gruner.huger.grunerhugel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        String password = user.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);

        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
