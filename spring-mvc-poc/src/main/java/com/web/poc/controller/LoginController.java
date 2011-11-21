package com.web.poc.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute(new Login());
		return "login/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Login login, BindingResult result) {
		if (result.hasErrors()) {
			return "login/login";
		}
		return "redirect:/controlpanel/" + login.getUser();
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String viewRegister() {
		return "login/registerView";
	}

}