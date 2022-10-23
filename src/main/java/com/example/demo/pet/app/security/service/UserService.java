package com.example.demo.pet.app.security.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.pet.app.security.entity.User;
import com.example.demo.pet.app.security.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository usuarioRepository;

	public Optional<User> getByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

	public boolean existsByUsername(String username) {
		return usuarioRepository.existsByUsername(username);
	}

	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}

	public void save(User user) {
		usuarioRepository.save(user);
	}

	public void createNewUser() {
		Date dateNow = new Date();
		dateNow.getTime();
		System.out.println(dateNow.getTime());
	}
}