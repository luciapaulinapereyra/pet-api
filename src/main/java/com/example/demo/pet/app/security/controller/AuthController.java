package com.example.demo.pet.app.security.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.demo.pet.app.response.ResponseHandler;
import com.example.demo.pet.app.security.dto.JwtDto;
import com.example.demo.pet.app.security.dto.LoginUsuario;
import com.example.demo.pet.app.security.dto.NewUser;
import com.example.demo.pet.app.security.entity.Role;
import com.example.demo.pet.app.security.entity.User;
import com.example.demo.pet.app.security.enums.NameRole;
import com.example.demo.pet.app.security.jwt.JwtProvider;
import com.example.demo.pet.app.security.service.RoleService;
import com.example.demo.pet.app.security.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/new")
	public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return ResponseHandler.generateResponse("Bad information", HttpStatus.BAD_REQUEST, null, true);
		if (userService.existsByUsername(newUser.getUsername()))
			return ResponseHandler.generateResponse("User already exists", HttpStatus.BAD_REQUEST, null, true);
		if (userService.existsByEmail(newUser.getEmail()))
			return ResponseHandler.generateResponse("Email already exists", HttpStatus.BAD_REQUEST, null, true);

		User user = new User(newUser.getUsername(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.getByNameRole(NameRole.ROLE_USER).get());
		if (newUser.getRoles().contains("admin"))
			roles.add(roleService.getByNameRole(NameRole.ROLE_ADMIN).get());
		user.setRoles(roles);
		userService.save(user);
		return ResponseHandler.generateResponse("User created!", HttpStatus.CREATED, null, false);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return ResponseHandler.generateResponse("Bad information", HttpStatus.BAD_REQUEST, null, true);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}

}