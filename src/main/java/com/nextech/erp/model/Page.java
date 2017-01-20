package com.nextech.erp.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the page database table.
 * 
 */
@Entity
@NamedQuery(name = "Page.findAll", query = "SELECT p FROM Page p")
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Size(min = 5, max = 15, message = "{Please enter greater than 5 or less than 30 character description}")
	private String description;

	
	@NotEmpty(message = "Please enter your menu")
	@Size(min = 2, max = 15, message = "{Please enter greater than 2 or less than 30 character menu}")
	private String menu;

	@NotEmpty(message = "Please enter your page name")
	@Size(min = 2, max = 15, message = "{Please enter greater than 2 or less than 30 character page name}")
	@Column(name = "page_name")
	private String pageName;
    
	@NotEmpty(message = "Please enter your submenu")
	@Size(min = 2, max = 15, message = "{Please enter greater than 2 or less than 30 character submenu}")
	private String submenu;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_date")
	private Timestamp updatedDate;
	private boolean isactive;

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@NotEmpty(message = "Please enter your url")
	private String url;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "page", cascade = CascadeType.ALL)
	// @OneToMany(mappedBy="page")
	private List<Usertypepageassociation> usertypepageassociations;

	public Page(long id) {
		this.id = id;
	}

	public Page() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenu() {
		return this.menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getPageName() {
		return this.pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getSubmenu() {
		return this.submenu;
	}

	public void setSubmenu(String submenu) {
		this.submenu = submenu;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Usertypepageassociation> getUsertypepageassociations() {
		return this.usertypepageassociations;
	}

	public void setUsertypepageassociations(
			List<Usertypepageassociation> usertypepageassociations) {
		this.usertypepageassociations = usertypepageassociations;
	}

	public Usertypepageassociation addUsertypepageassociation(
			Usertypepageassociation usertypepageassociation) {
		getUsertypepageassociations().add(usertypepageassociation);
		usertypepageassociation.setPage(this);

		return usertypepageassociation;
	}

	public Usertypepageassociation removeUsertypepageassociation(
			Usertypepageassociation usertypepageassociation) {
		getUsertypepageassociations().remove(usertypepageassociation);
		usertypepageassociation.setPage(null);

		return usertypepageassociation;
	}

}