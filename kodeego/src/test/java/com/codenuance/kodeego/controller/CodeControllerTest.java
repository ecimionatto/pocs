package com.codenuance.kodeego.controller;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import com.codenuance.kodeego.model.Code;
import com.codenuance.kodeego.model.CodeType;
import com.codenuance.kodeego.repository.CodeRepositoryImpl;
import com.codenuance.kodeego.to.CodeTo;

@RunWith(MockitoJUnitRunner.class)
public class CodeControllerTest {

	private static final String CODE_TYPE3 = "condition";

	private static final String CODE_TYPE2 = "if";

	private static final String CODE_TYPE1 = "java";

	@Mock
	CodeRepositoryImpl codeRepository;

	@Captor
	ArgumentCaptor<Code> codeCaptor;

	@Mock
	Principal principal;

	@Mock
	BindingResult bindingResult;

	@InjectMocks
	CodeController codeController = new CodeController();

	@Test
	public void shouldCreate() {

		CodeType codeTypeFix1 = new CodeType();
		codeTypeFix1.setType(CODE_TYPE1);
		when(codeRepository.findCodeType(CODE_TYPE1)).thenReturn(codeTypeFix1);
		when(codeRepository.findCodeType(CODE_TYPE2)).thenReturn(null);
		when(codeRepository.findCodeType(CODE_TYPE3)).thenReturn(null);

		CodeTo codeTo = new CodeTo();
		codeTo.setCode("if (true) return true;");
		codeTo.setComments("simple implementation of if statement with boolean");
		codeTo.setType("java if condition");
		codeController.create(codeTo, bindingResult, principal);

		verify(codeRepository).findCodeType(CODE_TYPE1);
		verify(codeRepository).findCodeType(CODE_TYPE2);
		verify(codeRepository).findCodeType(CODE_TYPE3);

		verify(codeRepository).create(codeCaptor.capture());

		Code capturedCode = codeCaptor.getValue();

		assertThat(capturedCode.getCode(),
				CoreMatchers.equalTo(codeTo.getCode()));

		codeTypeFix1.getCodes().add(capturedCode);
		CodeType codeType2 = new CodeType();
		codeType2.setType(CODE_TYPE2);
		codeType2.getCodes().add(capturedCode);
		CodeType codeType3 = new CodeType();
		codeType3.setType(CODE_TYPE3);
		codeType3.getCodes().add(capturedCode);

		assertThat(capturedCode.getTypes(),
				JUnitMatchers.hasItems(codeTypeFix1, codeType2, codeType3));
		assertThat(capturedCode.getCode(),
				CoreMatchers.equalTo(codeTo.getCode()));
		assertThat(capturedCode.getComments(),
				CoreMatchers.equalTo(codeTo.getComments()));

	}
}
