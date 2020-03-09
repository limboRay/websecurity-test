package ua.kpi.tef.websecuritytest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.kpi.tef.websecuritytest.entity.RoleType;
import ua.kpi.tef.websecuritytest.entity.User;
import ua.kpi.tef.websecuritytest.repository.UserRepo;
import ua.kpi.tef.websecuritytest.repository.UserRepository;

import java.util.Map;

@Controller
public class RegistrationController {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRepository userRepository;




    @GetMapping(value = {"/registration"})
    public String registrationPage() {

        return "registration.html";
    }

    @PostMapping(value = {"/registration"})
    public String addUser(User user, Map<String, Object> model) {

        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "user exists");
            return "registration.html";
        }


        user.setRole(RoleType.USER);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        userRepository.save(user);


        return "redirect:/login";
    }

}
