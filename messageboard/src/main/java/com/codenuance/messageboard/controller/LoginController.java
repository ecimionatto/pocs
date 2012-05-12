package com.codenuance.messageboard.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codenuance.messageboard.model.Login;
import com.codenuance.messageboard.repository.UserRepository;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserRepository userRepository;

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
		return "redirect:/user";
	}

}