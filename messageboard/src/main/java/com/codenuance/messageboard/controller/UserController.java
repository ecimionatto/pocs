package com.codenuance.messageboard.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codenuance.messageboard.model.User;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@RequestMapping(method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute(new User());
		return "user/user";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return "user/user";
		}
		return "redirect:/controlpanel/" + user.getUser();
	}

}