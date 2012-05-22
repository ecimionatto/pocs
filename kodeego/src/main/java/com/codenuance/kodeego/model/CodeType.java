package com.codenuance.kodeego.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(of = { "type" })
@ToString(exclude = { "codes", "subtypes", "supertypes" })
public class CodeType {

	@Id
	private String type;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "supertypes")
	private Set<CodeType> subtypes = new HashSet<CodeType>();

	@ManyToMany
	private Set<CodeType> supertypes = new HashSet<CodeType>();

	private String comments;

	@ManyToMany
	private Set<Code> codes = new HashSet<Code>();

}
