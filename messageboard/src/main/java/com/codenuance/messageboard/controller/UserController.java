package com.codenuance.messageboard.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codenuance.messageboard.model.User;
import com.codenuance.messageboard.repository.CrudOperatable;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private CrudOperatable<User> userRepository;

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

		userRepository.create(user);

		return "redirect:/controlpanel";
	}

}