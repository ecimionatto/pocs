package com.codenuance.kodeego.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codenuance.kodeego.model.CodeType;
import com.codenuance.kodeego.repository.CodeRepository;

@Controller
@Transactional
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	private CodeRepository codeRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String main(Model model) {
		return "search/search";
	}

	@RequestMapping(value = "getAllTypes", method = RequestMethod.GET)
	public @ResponseBody
	Collection<CodeType> getAllTypes() {
		return codeRepository.findAllCodeTypes();
	}

}