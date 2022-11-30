package gruner.huger.grunerhugel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import gruner.huger.grunerhugel.domain.user.UserDao;
import gruner.huger.grunerhugel.model.User;

@Controller
public class LoginController {
    
    @Autowired
    private UserDao userDao;

    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        

        System.out.println(user);
        if (userDao.validate(user.getUsername(), user.getPassword())) {
            return "redirect:/main";
        }

        return "redirect:/error";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
    
}
