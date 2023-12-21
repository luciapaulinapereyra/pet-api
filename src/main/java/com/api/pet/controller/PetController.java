package com.api.pet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.pet.model.PetModel;
import com.api.pet.service.PetService;

@RestController
@RequestMapping("/api/pets")
public class PetController {
	@Autowired
	private PetService petService;

	@GetMapping("/all")
	public ResponseEntity<Object> getPets() { // To get all the pets
		return this.petService.getPets();
	}

	@GetMapping(path = "/{category}") // To get a pet with a specific category
	public ResponseEntity<Object> findByName(@PathVariable("category") String category) {
		return this.petService.findByCategory(category);
	}

	@PostMapping() // to create a pet
	public ResponseEntity<Object> createPet(@RequestBody PetModel pet1) {
		return this.petService.createPet(pet1);

	}

	@PutMapping(path = "/{id}") // To edit a pet
	public ResponseEntity<Object> editPet(@PathVariable("id") Long id, @RequestBody PetModel pet) {
		return this.petService.editPet(id, pet);
	}

	@DeleteMapping(path = "/{id}") // To delete a pet
	public ResponseEntity<Object> deletePet(@PathVariable("id") Long id) {
		return this.petService.deletePet(id);
	}

}
