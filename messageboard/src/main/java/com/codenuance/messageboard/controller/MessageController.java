package com.codenuance.messageboard.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codenuance.messageboard.model.Message;
import com.codenuance.messageboard.model.User;
import com.codenuance.messageboard.repository.CrudOperatable;

@Controller
@RequestMapping(value = "/message")
public class MessageController {

	@Autowired
	private CrudOperatable<User> userRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String getView(Model model, Principal principal) {
		model.addAttribute(new Message());
		return "message/message";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addMessage(@Valid Message message, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return "message/message";
		}

		User user = userRepository.read(principal.getName());
		message.setUser(user);
		user.addMessage(message);
		userRepository.update(user);

		return "redirect:controlpanel";
	}
}