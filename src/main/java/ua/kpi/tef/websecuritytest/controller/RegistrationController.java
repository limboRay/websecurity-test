package ua.kpi.tef.websecuritytest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.tef.websecuritytest.dto.LanguageDTO;
import ua.kpi.tef.websecuritytest.entity.RoleType;
import ua.kpi.tef.websecuritytest.entity.User;
import ua.kpi.tef.websecuritytest.repository.UserRepo;
import ua.kpi.tef.websecuritytest.repository.UserRepository;
import ua.kpi.tef.websecuritytest.service.UserRepoService;
import ua.kpi.tef.websecuritytest.service.UserService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
public class RegistrationController {

    private LanguageDTO languageSwitcher = new LanguageDTO();

//    private final UserService userService;


//    @Autowired
//    public RegistrationController(UserService userService) {
//        this.userService = userService;
//    }

    @Autowired
    private UserRepoService userRepoService;

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

//        Optional<User> userFromDb = Optional.ofNullable(userRepo.findByUsername(user.getUsername()));
//        if (userFromDb != null) {
//            model.put("message", "user exists");
//            return "registration.html";
//        }


        user.setRole(RoleType.USER);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        userRepository.save(user);
//        userRepoService.saveNewUser(User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .firstName(user.getFirstName())
//                .firstNameCyr(user.getFirstNameCyr())
//                .lastName(user.getLastName())
//                .lastNameCyr(user.getLastNameCyr())
//                .role(RoleType.USER)
//                .email(user.getEmail())
//                .enabled(true)
//                .credentialsNonExpired(true)
//                .accountNonLocked(true)
//                .accountNonExpired(true).build());

        return "redirect:/login";
    }

}
