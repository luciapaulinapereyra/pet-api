package com.api.pet.service;

import com.api.pet.model.PetModel;
import com.api.pet.repository.IPetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    PetService petService;

    @Mock
    IPetRepository petRepository;

    @Test
    void createPet() {
        PetModel pet = new PetModel();
        pet.setName("test");
        pet.setCategory("test");
        pet.setDescription("test");
        pet.setYears(1);
        pet.setPicture("test");

        ResponseEntity<Object> res = petService.createPet(pet);

        assertEquals(201, res.getStatusCodeValue());

        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void createPet_Error() {
        PetModel pet = new PetModel();
        pet.setName("test");
        pet.setCategory("test");
        pet.setDescription("test");
        pet.setYears(1);
        pet.setPicture("test");

        when(petRepository.save(pet)).thenThrow(new RuntimeException("test"));
        ResponseEntity<Object> res = petService.createPet(pet);

        assertEquals(500, res.getStatusCodeValue());
    }

    @Test
    void getPets() {
        PetModel pet = new PetModel();
        pet.setName("test");
        pet.setCategory("test");
        pet.setDescription("test");
        pet.setYears(1);
        pet.setPicture("test");

        PageRequest pageRequest = PageRequest.of(0, 10);

        when(petRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(List.of(pet)));

        ResponseEntity<Object> res = petService.getPets();

        assertEquals(200, res.getStatusCodeValue());
        verify(petRepository, times(1)).findAll(any(PageRequest.class));

    }

    @Test
    void getPets_Empty() {


        PageRequest pageRequest = PageRequest.of(0, 10);

        when(petRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(List.of()));

        ResponseEntity<Object> res = petService.getPets();

        assertEquals(404, res.getStatusCodeValue());
        verify(petRepository, times(1)).findAll(any(PageRequest.class));

    }

    @Test
    void getPets_Error() {


        PageRequest pageRequest = PageRequest.of(0, 10);

        when(petRepository.findAll(pageRequest)).thenThrow(new RuntimeException("test"));

        ResponseEntity<Object> res = petService.getPets();

        assertEquals(500, res.getStatusCodeValue());
        verify(petRepository, times(1)).findAll(any(PageRequest.class));

    }

    @Test
    void editPet() {
        PetModel pet = new PetModel();
        pet.setName("test");
        pet.setCategory("test");
        pet.setDescription("test");
        pet.setYears(1);
        pet.setPicture("test");

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petRepository.save(pet)).thenReturn(pet);

        ResponseEntity<Object> res = petService.editPet(1L, pet);

        assertEquals(200, res.getStatusCodeValue());
        verify(petRepository, times(1)).findById(1L);
        verify(petRepository, times(1)).save(pet);

    }

    @Test
    void editPet_NotFound() {
        PetModel pet = new PetModel();
        pet.setName("test");
        pet.setCategory("test");
        pet.setDescription("test");
        pet.setYears(1);
        pet.setPicture("test");

        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Object> res = petService.editPet(1L, pet);

        assertEquals(404, res.getStatusCodeValue());

    }

    @Test
    void deletePet() {

        when(petRepository.findById(1L)).thenReturn(Optional.of(new PetModel()));
        ResponseEntity<Object> res = petService.deletePet(1L);

        assertEquals(201, res.getStatusCodeValue());
        verify(petRepository, times(1)).deleteById(1L);
    }

    @Test
    void deletePet_Error() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Object> res = petService.deletePet(1L);
        assertEquals(404, res.getStatusCodeValue());

    }

    @Test
    void findByCategory() {
        PetModel pet = new PetModel();
        pet.setName("test");
        pet.setCategory("test");
        pet.setDescription("test");
        pet.setYears(1);
        pet.setPicture("test");

        ArrayList<PetModel> list = new ArrayList();
        list.add(pet);
        when(petRepository.findByCategory("test")).thenReturn(list);

        ResponseEntity<Object> res = petService.findByCategory("test");

        assertEquals(200, res.getStatusCodeValue());
        verify(petRepository, times(1)).findByCategory("test");

    }

    @Test
    void findByCategory_NotFound() {

        when(petRepository.findByCategory("test")).thenReturn(new ArrayList<>());

        ResponseEntity<Object> res = petService.findByCategory("test");

        assertEquals(404, res.getStatusCodeValue());
        verify(petRepository, times(1)).findByCategory("test");

    }

    @Test
    void findByCategory_Error() {
        PetModel pet = new PetModel();
        pet.setName("test");
        pet.setCategory("test");
        pet.setDescription("test");
        pet.setYears(1);
        pet.setPicture("test");

        ArrayList<PetModel> list = new ArrayList();
        list.add(pet);
        when(petRepository.findByCategory("test")).thenThrow(new RuntimeException("test"));

        ResponseEntity<Object> res = petService.findByCategory("test");

        assertEquals(500, res.getStatusCodeValue());

    }
}