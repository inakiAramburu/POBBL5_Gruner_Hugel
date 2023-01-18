package gruner.huger.grunerhugel.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gruner.huger.grunerhugel.config.URI;
import gruner.huger.grunerhugel.domain.repository.RoleRepository;
import gruner.huger.grunerhugel.domain.repository.UserRepository;
import gruner.huger.grunerhugel.model.User;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/edit/{id}")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("new_user", new User());
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                model.addAttribute("old_user", user.get());
            }
        } catch (UsernameNotFoundException e) {
            e.getMessage();
        }
        return "user/user-edit";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable int id) {
        userRepository.deleteById(id);
        return "redirect:" + URI.HOME_ADMIN.getPath();
    }

    @PostMapping(value = "/update/{id}")
    public String update(@ModelAttribute("new_user") User user, @PathVariable int id) {
        User oldUser = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setPassword(oldUser.getPassword());
        user.setId(oldUser.getId());
        userRepository.save(user);
        return "redirect:" + URI.HOME_ADMIN.getPath();
    }
}
