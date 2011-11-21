package com.web.poc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/controlpanel")
public class ControlPanelController {

	@RequestMapping(value = "{user}", method = RequestMethod.GET)
	public String getView(@PathVariable String user, Model model) {
		Login login = new Login();
		login.setUser(user);
		model.addAttribute(login);
		return "controlpanel/controlpanel";
	}

	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public @ResponseBody
	Login getUserWithTag() {
		Login login = new Login();
		login.setUser("test");
		return login;
	}
}