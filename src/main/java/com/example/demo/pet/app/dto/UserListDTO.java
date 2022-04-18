package com.example.demo.pet.app.dto;

import com.example.demo.pet.app.model.UserModel;

//Class to help transport user data
public class UserListDTO {

	private Long id;
	private String name;
	private String lastname;
	private String email;
    private String password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public UserListDTO(UserModel user) {
		this.id = user.getId();
		this.name = user.getName();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.password = user.getPassword();

	}

	public UserListDTO() {
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

	public String getLastName() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}