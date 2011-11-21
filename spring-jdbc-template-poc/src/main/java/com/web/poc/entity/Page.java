package com.web.poc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Page implements Serializable {
	public static final String BY_PARENT_NAME = "selectPageByParentName";
	private static final long serialVersionUID = 3854207120672725592L;

	private List<Page> children;

	private String filename;

	private String filenameAlias;

	private Long hidden;

	private Long id;

	private Long imageId;

	private Date lastFileNameChange;

	private Date lastPublishDate;

	private Layout layout;

	private String name;

	private Page parent;

	private String properties;

	private Long sequenceNumber;

	public List<Page> getChildren() {
		return children;
	}

	public String getFilename() {
		return filename;
	}

	public String getFilenameAlias() {
		return filenameAlias;
	}

	public Long getHidden() {
		return hidden;
	}

	public Long getId() {
		return id;
	}

	public Long getImageId() {
		return imageId;
	}

	public Date getLastFileNameChange() {
		return lastFileNameChange;
	}

	public Date getLastPublishDate() {
		return lastPublishDate;
	}

	public Layout getLayout() {
		return layout;
	}

	public String getName() {
		return name;
	}

	public Page getParent() {
		return parent;
	}

	public String getProperties() {
		return properties;
	}

	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setChildren(List<Page> childs) {
		this.children = childs;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setFilenameAlias(String filenameAlias) {
		this.filenameAlias = filenameAlias;
	}

	public void setHidden(Long hidden) {
		this.hidden = hidden;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public void setLastFileNameChange(Date lastFileNameChange) {
		this.lastFileNameChange = lastFileNameChange;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		this.lastPublishDate = lastPublishDate;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParent(Page parent) {
		this.parent = parent;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

}
