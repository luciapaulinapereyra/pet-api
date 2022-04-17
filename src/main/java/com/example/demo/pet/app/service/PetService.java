package com.example.demo.pet.app.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.pet.app.model.PetModel;
import com.example.demo.pet.app.repository.IPetRepository;
import com.example.demo.pet.app.response.ResponseHandler;

@Service
public class PetService {
	@Autowired
	IPetRepository petRepo;
	
	
	//create & save the pet
	public ResponseEntity<Object> createPet(PetModel pet) {
		
		PetModel newPet = new PetModel();
		newPet.setName(pet.getName());
		newPet.setCategory(pet.getCategory());
		newPet.setDescription(pet.getDescription());
		newPet.setYears(pet.getYears());
		newPet.setId(pet.getId());
		newPet.setPicture(pet.getPicture());
		
		try {
			PetModel savedPet = petRepo.save(newPet);
		   return  ResponseHandler.generateResponse("Pet saved!", HttpStatus.CREATED, savedPet, false);
		} catch (Exception err) {
			return  ResponseHandler.generateResponse(err.toString(), HttpStatus.INTERNAL_SERVER_ERROR, null, true);
		}
	}
	
	
	//get all the pets
	public ResponseEntity<Object> getPets() {
         
		try {
			ArrayList<PetModel> pets = new ArrayList<PetModel>();
			petRepo.findAll().forEach(pets::add);
			
			if (pets.isEmpty()) {
				return ResponseHandler.generateResponse("the list is empty!", HttpStatus.NOT_FOUND, null, true);
			}
			
			return ResponseHandler.generateResponse("", HttpStatus.OK, pets, false);
			
			
		} catch (Exception e) {
			 return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, null, true);
		}
	}
	
	
	
	//edit a pet 
	public ResponseEntity<Object> editPet(Long id, PetModel petRequest) {
		Optional<PetModel> pet = petRepo.findById(id);
		
		if(!pet.isPresent())
			return ResponseHandler.generateResponse("Error, pet not found", HttpStatus.NOT_FOUND, null, true);
		
		PetModel newPet = pet.get();
		if(petRequest.getName() != null)
			newPet.setName(petRequest.getName());
		if(petRequest.getDescription() != null)
			newPet.setDescription(petRequest.getDescription());
		if(petRequest.getCategory() != null)
			newPet.setCategory(petRequest.getCategory());
		if(petRequest.getYears() != 0)
			newPet.setYears(petRequest.getYears());
		if(petRequest.getPicture()!= null)
			newPet.setPicture(petRequest.getPicture());
        
		
		PetModel editPet = petRepo.save(newPet);
		return ResponseHandler.generateResponse("Pet updated!", HttpStatus.OK, editPet, false);

	}
	
	  
	//delete a pet
    public ResponseEntity<Object> deletePet(Long id) { 
        try {
            petRepo.deleteById(id);

            return ResponseHandler.generateResponse("Pet deleted", HttpStatus.CREATED, null, false);
        } catch (Exception err) {
            return ResponseHandler.generateResponse("Error,pet not found", HttpStatus.NOT_FOUND, null, true);
        }

    }
	 
    //find by category
    public ResponseEntity<Object> findByCategory(String category) {
        try {
            ArrayList<PetModel> pets = new ArrayList<PetModel>();
            petRepo.findByCategory(category).forEach(pets::add);
            if (pets.isEmpty()) {
                return ResponseHandler.generateResponse("There are no pets with that category ", HttpStatus.NOT_FOUND,
                        null, false);
            }
            return ResponseHandler.generateResponse("", HttpStatus.OK, pets, false);
        } catch (Exception err) {
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, null, true);
        }
    }
    
	

}
