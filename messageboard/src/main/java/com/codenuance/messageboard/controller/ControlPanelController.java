package com.codenuance.messageboard.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codenuance.messageboard.model.Login;
import com.codenuance.messageboard.model.User;
import com.codenuance.messageboard.repository.CrudOperatable;

@Controller
@RequestMapping(value = "/controlpanel")
public class ControlPanelController {

	@Autowired
	private CrudOperatable<User> userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String getView(Model model, Principal principal) {
		User user = userRepository.read(principal.getName());
		model.addAttribute("user", user);
		model.addAllAttributes(user.getMessages());
		return "controlpanel/controlpanel";
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public @ResponseBody
	Login getUserWithTag() {
		Login login = new Login();
		login.setUserMessage("test");
		return login;
	}

}