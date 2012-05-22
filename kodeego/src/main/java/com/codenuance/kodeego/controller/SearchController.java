package com.codenuance.kodeego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Transactional
@RequestMapping(value = "/search")
public class SearchController {

	@RequestMapping(method = RequestMethod.GET)
	public String main(Model model) {
		return "search/search";
	}
	
	
}