package com.codenuance.messageboard.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Login {
	@NotNull
	@Size(min = 1, max = 25)
	private String user;

	@NotNull
	@Size(min = 1, max = 25)
	private String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String name) {
		this.password = name;
	}

}
