package com.codenuance.kodeego.controller;

import java.util.ArrayList;
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

	class CodeTypeTo {
		public CodeTypeTo() {
		}

		CodeTypeTo(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		private String id;
		private String name;
	}

	@RequestMapping(value = "getAllTypes", method = RequestMethod.GET)
	public @ResponseBody
	Collection<CodeTypeTo> getAllTypes() {
		Collection<CodeType> findAllCodeTypes = codeRepository
				.findAllCodeTypes();

		ArrayList<CodeTypeTo> arrayList = new ArrayList<CodeTypeTo>();
		for (CodeType code : findAllCodeTypes) {
			arrayList.add(new CodeTypeTo(code.getType(), code.getType()));
		}

		return arrayList;
	}

}