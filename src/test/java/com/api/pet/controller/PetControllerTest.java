package com.api.pet.controller;

import com.api.pet.model.PetModel;
import com.api.pet.service.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PetService petService;

    @Test
    void getPets() throws Exception {
        when(petService.getPets()).thenReturn(ResponseEntity.ok("hello"));

        mockMvc.perform(get("/api/pets/all"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));

    }

    @Test
    void findByName() throws Exception  {
        when(petService.findByCategory("cat")).thenReturn(ResponseEntity.ok("hello"));

        mockMvc.perform(get("/api/pets/cat"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    void createPet() throws Exception {
        PetModel pet = new PetModel();
        when(petService.createPet(pet)).thenReturn(ResponseEntity.ok("hello"));

        mockMvc.perform(post("/api/pets")
                .content(new ObjectMapper().writeValueAsString(pet))
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void editPet() throws Exception {
        PetModel pet = new PetModel();
        when(petService.editPet(any(), any())).thenReturn(ResponseEntity.ok("hello"));

        mockMvc.perform(put("/api/pets/1")
                .content(new ObjectMapper().writeValueAsString(pet))
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePet() throws Exception  {
        when(petService.deletePet(any())).thenReturn(ResponseEntity.ok("hello"));

        mockMvc.perform(delete("/api/pets/1"))
                .andExpect(status().isOk());
    }
}