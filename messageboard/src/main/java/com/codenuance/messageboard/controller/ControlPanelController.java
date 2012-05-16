package com.codenuance.messageboard.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codenuance.messageboard.model.Message;
import com.codenuance.messageboard.model.User;
import com.codenuance.messageboard.repository.CrudOperatable;

@Controller
@Transactional
@RequestMapping(value = "/controlpanel")
public class ControlPanelController {

	@Autowired
	private CrudOperatable<User> userRepository;

	@RequestMapping(value = "messages", method = RequestMethod.GET)
	public @ResponseBody
	List<Message> getMessages(Model model, Principal principal) {
		User user = userRepository.read(principal.getName());
		return user.getObservedMessages();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getView(Model model, Principal principal) {
		if (principal == null) {
			return "welcome";
		}
		User user = userRepository.read(principal.getName());
		if (user == null) {
			return "welcome";
		}
		user.getObservedMessages();
		model.addAttribute("user", user);
		return "controlpanel/controlpanel";
	}

	@RequestMapping(value = "/searchUsers/{id}", method = RequestMethod.GET)
	public @ResponseBody
	User getUserWithTag(Model model, @PathVariable String id,
			Principal principal) {
		User userToBeFollowed = userRepository.read(id);
		if (userToBeFollowed == null) {
			return null;
		}

		User principalUser = userRepository.read(principal.getName());
		principalUser.getObservedUsers().add(userToBeFollowed);
		userToBeFollowed.getObservingUsers().add(principalUser);
		User user = userRepository.update(principalUser);
		user.getObservedMessages();
		return userToBeFollowed;
	}

}