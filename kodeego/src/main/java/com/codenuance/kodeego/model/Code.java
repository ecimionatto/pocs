package com.codenuance.kodeego.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Data
@JsonIgnoreProperties({ "types" })
@EqualsAndHashCode(exclude = { "types" })
@ToString(exclude = { "types" })
@NamedQuery(name = Code.FIND_BY_TYPES, query = "select distinct c from Code as c inner join c.types t where t.type in :types")
public class Code {

	public static final String FIND_BY_TYPES = "findCodeByTypes";

	@Id
	private String id = UUID.randomUUID().toString();

	@Basic(optional = false)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CodeType> types = new HashSet<CodeType>();

	@Column(length = 60000)
	@Basic(optional = false)
	private String code;

	private String comments;

}
