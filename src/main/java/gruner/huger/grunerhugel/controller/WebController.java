package gruner.huger.grunerhugel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    @GetMapping(path = {"/","/login"})
    public String index() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h2> Welcome Admin!</h2>";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/create")
    public String create() {
        return "user/user-form";
    }
}
