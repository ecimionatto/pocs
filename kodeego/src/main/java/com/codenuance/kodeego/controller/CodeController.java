package com.codenuance.kodeego.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codenuance.kodeego.model.Code;
import com.codenuance.kodeego.model.CodeType;
import com.codenuance.kodeego.repository.CodeRepository;
import com.codenuance.kodeego.to.CodeTo;

@Controller
@Transactional
@RequestMapping(value = "/code")
public class CodeController {

	@Autowired
	private CodeRepository codeRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String getView(Model model, Principal principal) {
		model.addAttribute(new CodeTo());
		return "code/code";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid CodeTo codeTo, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return "code/code";
		}

		Code code = new Code();
		BeanUtils.copyProperties(codeTo, code);

		for (String type : codeTo.getType().split("[\\s+,;]")) {
			CodeType persistendCodeType = codeRepository.findCodeType(type);
			if (persistendCodeType != null) {
				persistendCodeType.getCodes().add(code);
				code.getTypes().add(persistendCodeType);
			} else {
				CodeType codeType = new CodeType();
				codeType.setType(type);
				codeType.getCodes().add(code);
				code.getTypes().add(codeType);
			}
		}

		codeRepository.create(code);

		return "redirect:search";
	}
}