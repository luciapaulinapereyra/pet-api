package com.example.demo.pet.app.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pet.app.model.PetModel;

@Repository
public interface IPetRepository extends JpaRepository<PetModel, Long>{
  ArrayList<PetModel> findByCategory(String category);
}
