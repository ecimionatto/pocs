package com.codenuance.kodeego.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codenuance.kodeego.model.Message;
import com.codenuance.kodeego.model.User;
import com.codenuance.kodeego.repository.CrudOperatable;

@Controller
@Transactional
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

		if (principal==null) {
			return "welcome";
		}
		
		User user = userRepository.read(principal.getName());
		message.setUser(user);
		user.addMessage(message);
		userRepository.update(user);

		return "redirect:controlpanel";
	}
}