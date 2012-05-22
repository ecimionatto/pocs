package com.codenuance.kodeego.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codenuance.kodeego.model.User;
import com.codenuance.kodeego.repository.CrudOperatable;

@Controller
@Transactional
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

		return "redirect:/welcome";
	}

}