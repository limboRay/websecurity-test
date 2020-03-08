package ua.kpi.tef.websecuritytest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.kpi.tef.websecuritytest.dto.LanguageDTO;
import ua.kpi.tef.websecuritytest.service.UserService;

@Controller
public class RegistrationController {

    private LanguageDTO languageSwitcher = new LanguageDTO();
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = {"/registration"})
    public String registrationPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout,
                                   Model model) {

        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login.html";
    }
}
