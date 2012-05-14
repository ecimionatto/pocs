package com.codenuance.messageboard.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.codenuance.messageboard.model.Login;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserDetailsService userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String getCreateForm(Model model, Principal principal) {
		Login login = new Login();
		if (principal != null) {
			login.setUserMessage("welcome: " + principal.getName());
		} else {
			login.setUserMessage("please sign-in");
		}
		model.addAttribute(login);
		return "login/login";
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public String create(@Valid Login login, BindingResult result,
//			WebRequest request) {
//
//		UserDetails loadUserByUsername = userRepository
//				.loadUserByUsername(login.getUser());
//		if (loadUserByUsername == null) {
//			result.addError(new ObjectError("user", "invalid user/password"));
//		}
//
//		if (result.hasErrors()) {
//			return "login/login";
//		}
//
//		request.setAttribute("user", loadUserByUsername,
//				WebRequest.SCOPE_SESSION);
//		return "redirect:/controlpanel";
//	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String viewRegister() {
		return "redirect:/user";
	}

}