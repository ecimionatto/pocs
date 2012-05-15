package com.codenuance.messageboard.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
public class User implements UserDetails {

	class GrantedAuthorityCustom implements GrantedAuthority {

		private static final String ROLE_USER = "ROLE_USER";
		private static final long serialVersionUID = 3456648447846886316L;

		public String getAuthority() {
			return ROLE_USER;
		}

	}

	private static final long serialVersionUID = -3576369886698606371L;

	@OrderBy ("timestamp DESC")
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Message> messages;

	@NotNull
	@Size(min = 1, max = 25)
	private String password;

	@Id
	@NotNull
	@Size(min = 1, max = 25)
	private String username;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> arrayList = new ArrayList<GrantedAuthority>();
		arrayList.add(new GrantedAuthorityCustom());
		return arrayList;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void addMessage(Message message) {
		messages.add(message);
	}

}
