package gruner.huger.grunerhugel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
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
}
