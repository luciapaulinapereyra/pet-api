package com.api.pet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PetModel {
	@Id
	@Column(name = "id", length = 50)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "years")
	private int years;
	@Column(name = "description")
	private String description;
	@Column(name = "category")
	private String category;
	@Column(name = "picture")
	private String picture;
	@Column(name = "lost_zone")
	private String lost_zone;

	public PetModel(String name, int years, String description, String category, String picture,
			String lost_zone) {
		this.name = name;
		this.years = years;
		this.description = description;
		this.category = category;
		this.picture = picture;
		this.lost_zone = lost_zone;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public PetModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLost_zone() {
		return lost_zone;
	}

	public void setLost_zone(String lost_zone) {
		this.lost_zone = lost_zone;
	}

}
