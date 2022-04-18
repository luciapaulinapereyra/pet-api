package com.example.demo.pet.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.pet.app.model.UserModel;
import com.example.demo.pet.app.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping()
	public ResponseEntity<Object> createUser(@RequestBody UserModel user) { // To create a user
		return this.userService.createUser(user);
	}

	@PutMapping(path = "/{id}") // To edit a user
	public ResponseEntity<Object> modifyUser(@PathVariable("id") Long id, @RequestBody UserModel userRequest) {
		return this.userService.editUser(id, userRequest);
	}

	@DeleteMapping(path = "/{id}") // To delete a user
	public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
		return this.userService.deleteUser(id);

	}
}
