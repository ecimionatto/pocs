package com.codenuance.messageboard.controller;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import com.codenuance.messageboard.model.User;
import com.codenuance.messageboard.repository.CrudOperatable;

@RunWith(MockitoJUnitRunner.class)
public class ControlPanelControllerTest {

	private static final String PRINCIPAL_USER_ID = "pacosancho";

	private static final String USER_ID = "ecimionatto";

	@Captor
	ArgumentCaptor<User> userCaptor;

	@Mock
	Principal principal;

	@Mock
	Model model;
	
	@Mock
	CrudOperatable<User> crudOperatable;

	@InjectMocks
	ControlPanelController controlPanelController = new ControlPanelController();

	@Test
	public void shouldStopFollowing() {

		when(principal.getName()).thenReturn(PRINCIPAL_USER_ID);

		User principalUser = new User();
		principalUser.setUsername(PRINCIPAL_USER_ID);

		User usertoStopFollowing = new User();
		usertoStopFollowing.setUsername(USER_ID);
		usertoStopFollowing.getObservingUsers().add(principalUser);
		principalUser.getObservedUsers().add(usertoStopFollowing);

		when(crudOperatable.read(USER_ID)).thenReturn(usertoStopFollowing);
		when(crudOperatable.read(PRINCIPAL_USER_ID)).thenReturn(principalUser);
		when(crudOperatable.update(principalUser)).thenReturn(principalUser);

		controlPanelController.stopFollowing(model, USER_ID, principal);

		verify(crudOperatable).read(USER_ID);
		verify(crudOperatable).read(PRINCIPAL_USER_ID);
		verify(crudOperatable).update(userCaptor.capture());
		verify(model).addAttribute(principalUser);
		
		User capturedUSer = userCaptor.getValue();
		assertThat(capturedUSer.getObservedUsers().size(),
				CoreMatchers.equalTo(0));

	}
}
