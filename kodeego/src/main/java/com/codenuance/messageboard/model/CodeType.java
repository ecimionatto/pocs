package com.codenuance.messageboard.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class CodeType {

	@Id
	private String id = UUID.randomUUID().toString();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "superType")
	private Set<CodeType> subtype = new HashSet<CodeType>();

	@ManyToMany
	private Set<CodeType> superType = new HashSet<CodeType>();

	private String description;

}
