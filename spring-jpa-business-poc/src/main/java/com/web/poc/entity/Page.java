package com.web.poc.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pages_temp")
@NamedQuery(name = Page.BY_PARENT_NAME, query = "select o1 from Page o1, Page o2 where o1.parent = o2.parent and o2.name = :parent")
public class Page implements Serializable {
	public static final String BY_PARENT_NAME = "selectPageByParentName";
	private static final long serialVersionUID = 3854207120672725592L;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	private List<Page> children;

	@Column(length = 64)
	private String filename;

	@Column(name = "filename_alias", length = 64)
	private String filenameAlias;

	@Column(length = 22)
	private Long hidden;

	@Id
	@Column(length = 22)
	private Long id;

	@Column(name = "image_id")
	private Long imageId;

	@Column(name = "last_filenamechange_date")
	private Date lastFileNameChange;

	@Column(name = "last_publish_date")
	private Date lastPublishDate;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Layout layout;

	@Column(name = "name", length = 30)
	private String name;

	@ManyToOne
	@JoinColumn(name = "parent")
	private Page parent;

	private Blob properties;

	@Column(name = "seq_no")
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

	public Blob getProperties() {
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

	public void setProperties(Blob properties) {
		this.properties = properties;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

}
