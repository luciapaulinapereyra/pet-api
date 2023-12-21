package com.api.pet.service;

import java.util.ArrayList;
import java.util.Optional;

import com.api.pet.repository.IPetRepository;
import com.api.pet.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.pet.model.PetModel;

@Service
public class PetService {
    @Autowired
    IPetRepository petRepo;


    //create & save the pet
    public ResponseEntity<Object> createPet(PetModel pet) {
        try {
            PetModel savedPet = petRepo.save(pet);
            return ResponseHandler.generateResponse("Pet saved!", HttpStatus.CREATED, savedPet, false);
        } catch (Exception err) {
            return ResponseHandler.generateResponse(err.toString(), HttpStatus.INTERNAL_SERVER_ERROR, null, true);
        }
    }


    //get all the pets
    public ResponseEntity<Object> getPets() {

        try {
            Page<PetModel> petPage = petRepo.findAll(PageRequest.of(0, 10));

            if (petPage.isEmpty()) {
                return ResponseHandler.generateResponse("the list is empty!", HttpStatus.NOT_FOUND, null, true);
            }

            return ResponseHandler.generateResponse("", HttpStatus.OK, petPage, false);


        } catch (Exception e) {
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, null, true);
        }
    }


    //edit a pet
    public ResponseEntity<Object> editPet(Long id, PetModel petRequest) {
        Optional<PetModel> pet = petRepo.findById(id);

        if (!pet.isPresent())
            return ResponseHandler.generateResponse("Error, pet not found", HttpStatus.NOT_FOUND, null, true);

        PetModel newPet = pet.get();
        newPet.setName(petRequest.getName());
        newPet.setDescription(petRequest.getDescription());
        newPet.setCategory(petRequest.getCategory());
        newPet.setYears(petRequest.getYears());
        newPet.setPicture(petRequest.getPicture());


        PetModel editPet = petRepo.save(newPet);
        return ResponseHandler.generateResponse("Pet updated!", HttpStatus.OK, editPet, false);

    }


    //delete a pet
    public ResponseEntity<Object> deletePet(Long id) {
        Optional<PetModel> pet = petRepo.findById(id);
        if(!pet.isPresent())
            return ResponseHandler.generateResponse("Error, pet not found", HttpStatus.NOT_FOUND, null, true);
        petRepo.deleteById(id);
        return ResponseHandler.generateResponse("Pet deleted", HttpStatus.CREATED, null, false);

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
