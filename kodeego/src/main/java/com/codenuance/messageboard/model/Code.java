package com.codenuance.messageboard.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Code {

	@Id
	private String id = UUID.randomUUID().toString();

	@ManyToMany
	private Set<CodeType> type = new HashSet<CodeType>();

	@Column(length = 60000)
	@Basic(optional = false)
	private String code;

}
