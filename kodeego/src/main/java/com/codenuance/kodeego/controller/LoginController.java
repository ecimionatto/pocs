package com.codenuance.kodeego.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codenuance.kodeego.model.Login;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

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

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String viewRegister() {
		return "redirect:/user";
	}

}