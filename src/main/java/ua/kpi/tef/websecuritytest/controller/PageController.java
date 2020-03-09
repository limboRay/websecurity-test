package ua.kpi.tef.websecuritytest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.RedirectView;
import ua.kpi.tef.websecuritytest.dto.LanguageDTO;
import ua.kpi.tef.websecuritytest.dto.UserDTO;
import ua.kpi.tef.websecuritytest.entity.RoleType;
import ua.kpi.tef.websecuritytest.entity.User;
import ua.kpi.tef.websecuritytest.service.UserService;

import java.util.List;
import java.util.Locale;


@Controller
public class PageController implements WebMvcConfigurer {

	private LanguageDTO languageSwitcher = new LanguageDTO();
	private final UserService userService;

	@Autowired
	public PageController(UserService userService) {
		this.userService = userService;
	}

	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("l"); //token that is expected after /? in url for locale choice
		return localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(localeChangeInterceptor());
	}


	@RequestMapping(value = {"/login"})
	public String loginPage(@RequestParam(value = "error", required = false) String error,
							@RequestParam(value = "logout", required = false) String logout,
							Model model) {
		if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
			return adminPage(model);
		}
		model.addAttribute("error", error != null);
		model.addAttribute("logout", logout != null);
		return "login.html";
	}


	@RequestMapping(value = { "/admin"})
	public String adminPage( Model model){
		languageSwitcher.setChoice(LocaleContextHolder.getLocale().toString());
		model.addAttribute("language", languageSwitcher);
		model.addAttribute("supported", languageSwitcher.getSupportedLanguages());

		System.out.println("Obtained locale code from the front end: " + languageSwitcher.getChoice());




		List<User> allUsers = userService.getAllUsers().getUsers();
		if (languageSwitcher.isLocaleCyrillic()){
			for (User user : allUsers) {
				user.setFirstName(user.getFirstNameCyr());
				user.setLastName(user.getLastNameCyr());
			}
		}


		model.addAttribute("language", languageSwitcher);
		model.addAttribute("users", allUsers);
		return "admin.html";
	}


	@RequestMapping(value = { "/user"})
	public String userPage( Model model){
		languageSwitcher.setChoice(LocaleContextHolder.getLocale().toString());
		model.addAttribute("language", languageSwitcher);
		model.addAttribute("supported", languageSwitcher.getSupportedLanguages());

		System.out.println("Obtained locale code from the front end: " + languageSwitcher.getChoice());

		return "user.html";
	}
	@RequestMapping("/success")
	public RedirectView localRedirect() {
		RedirectView redirectView = new RedirectView();

		if (currentUserIsAdmin()) {
			redirectView.setUrl("/admin");
		} else {
			redirectView.setUrl("/user");
		}

		return redirectView;
	}

	private boolean currentUserIsAdmin() {
		User currentUser = currentUserIsUser();
		return currentUser.getRole() == RoleType.ADMIN;
	}

	private User currentUserIsUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDTO currentUser;

		try {
			currentUser = (UserDTO) auth.getPrincipal();
		} catch (ClassCastException e) {
			return new User();
		}


		return currentUser.getUser();
	}



}

