package com.codenuance.messageboard.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
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

	@RequestMapping(value = "observedUsers", method = RequestMethod.GET)
	public @ResponseBody
	Collection<User> getObservedUSers(Model model, Principal principal) {
		User user = userRepository.read(principal.getName());
		return user.getObservedUsers();
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

	@RequestMapping(value = "/follow/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Collection<User> getUserWithTag(Model model, @PathVariable String id,
			Principal principal) {
		User userToBeFollowed = userRepository.read(id);
		if (userToBeFollowed == null) {
			return null;
		}

		if (!principal.getName().equals(id)) {
			User principalUser = userRepository.read(principal.getName());
			principalUser.getObservedUsers().add(userToBeFollowed);
			userToBeFollowed.getObservingUsers().add(principalUser);
			User user = userRepository.update(principalUser);
			return user.getObservedUsers();

		}

		return new ArrayList<User>();
	}

	@RequestMapping(value = "/stopFollowing/{id}", method = RequestMethod.GET)
	public String stopFollowing(Model model, @PathVariable String id, Principal principal) {
		User userToBeFollowed = userRepository.read(id);
		User principalUser = userRepository.read(principal.getName());
		if (userToBeFollowed != null) {
			if (!principal.getName().equals(id)) {
				principalUser.getObservedUsers().remove(userToBeFollowed);
				userToBeFollowed.getObservingUsers().remove(principalUser);
				userRepository.update(principalUser);
			}
		}
		principalUser.getObservedMessages();
		model.addAttribute(principalUser);
		return "redirect:/controlpanel";
	}

}