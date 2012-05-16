package com.codenuance.messageboard.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@JsonIgnoreProperties({ "observedUsers", "observingUser", "messages" })
@Entity
public class User implements UserDetails {

	@Override
	public String toString() {
		return "User [username=" + username + "]";
	}

	public Set<User> getObservedUsers() {
		return observedUsers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public void setObservedUsers(Set<User> observedUsers) {
		this.observedUsers = observedUsers;
	}

	public User getObservingUser() {
		return observingUser;
	}

	public void setObservingUser(User observingUser) {
		this.observingUser = observingUser;
	}

	class GrantedAuthorityCustom implements GrantedAuthority {

		private static final String ROLE_USER = "ROLE_USER";
		private static final long serialVersionUID = 3456648447846886316L;

		public String getAuthority() {
			return ROLE_USER;
		}

	}

	private static final long serialVersionUID = -3576369886698606371L;

	@OrderBy("timestamp DESC")
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Message> messages;

	@NotNull
	@Size(min = 1, max = 25)
	private String password;

	@Id
	@NotNull
	@Size(min = 1, max = 25)
	private String username;

	@JsonIgnore
	@OneToMany(mappedBy = "observingUser", fetch = FetchType.EAGER)
	private Set<User> observedUsers;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_parent")
	private User observingUser;

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
