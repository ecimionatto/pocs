package com.web.poc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "layout_temp")
public class Layout implements Serializable {

	private static final long serialVersionUID = 8918821930246239903L;

	@Id
	private Long id;

	private String name;

	@Column(name = "page_type", length = 22)
	private Long type;

	@Column(length = 22)
	private Long thumbnail;

	@Column(length = 200)
	private String description;

	@OneToMany(mappedBy = "layout")
	private List<Page> pages;

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Page> getPages() {
		return pages;
	}

	public Long getThumbnail() {
		return thumbnail;
	}

	public Long getType() {
		return type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public void setThumbnail(Long thumbnail) {
		this.thumbnail = thumbnail;
	}

	public void setType(Long type) {
		this.type = type;
	}

}
