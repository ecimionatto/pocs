package com.codenuance.messageboard.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Message {

	@Id
	private String id = UUID.randomUUID().toString();

	@NotNull
	private String message;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp = new Date();

	@ManyToOne
	private User user;

	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public User getUser() {
		return user;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
