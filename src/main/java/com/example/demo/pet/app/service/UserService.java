package com.example.demo.pet.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.pet.app.dto.UserListDTO;
import com.example.demo.pet.app.model.UserModel;
import com.example.demo.pet.app.repository.IUserRepository;
import com.example.demo.pet.app.response.ResponseHandler;

@Service
public class UserService {

	@Autowired
	IUserRepository userRepository;

	public ResponseEntity<Object> createUser(UserModel user) { // to create and save a user
		try {
			Optional<UserModel> existingUserByEmail = userRepository.findByEmail(user.getEmail());
			if (existingUserByEmail.isPresent())
				return ResponseHandler.generateResponse("The email already exists", HttpStatus.BAD_REQUEST, null, true);

			UserModel newUser = new UserModel();
			newUser.setName(user.getName());
			newUser.setEmail(user.getEmail());
			newUser.setLastname(user.getLastname());
			newUser.setPassword(user.getPassword());
			newUser.setId(user.getId());

			UserModel savedUser = userRepository.save(newUser);
			UserListDTO userResponse = new UserListDTO(savedUser);
			return ResponseHandler.generateResponse("User created!", HttpStatus.CREATED, userResponse, false);
		} catch (Exception err) {
			return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, null, true);
		}

	}

	public ResponseEntity<Object> deleteUser(Long id) { // to delete a user
		try {
			userRepository.deleteById(id);

			return ResponseHandler.generateResponse("User deleted", HttpStatus.CREATED, null, false);
		} catch (Exception err) {
			return ResponseHandler.generateResponse("Error,user not found", HttpStatus.NOT_FOUND, null, true);
		}
	}

	public ResponseEntity<Object> editUser(Long id, UserModel userRequest) { // to edit a user
		Optional<UserModel> user = userRepository.findById(id);

		if (!user.isPresent())
			return ResponseHandler.generateResponse("Error, user not found", HttpStatus.NOT_FOUND, null, true);

		UserModel userm = user.get();
		if (userRequest.getName() != null)
			userm.setName(userRequest.getName());
		if (userRequest.getEmail() != null)
			userm.setEmail(userRequest.getEmail());
		if (userRequest.getLastname() != null)
			userm.setLastname(userRequest.getLastname());
		if (userRequest.getPassword() != null)
			userm.setPassword(userRequest.getPassword());

		UserModel modifiedUser = userRepository.save(userm);
		UserListDTO userResponse = new UserListDTO(modifiedUser);
		return ResponseHandler.generateResponse("User updated!", HttpStatus.OK, userResponse, false);
	}
}
