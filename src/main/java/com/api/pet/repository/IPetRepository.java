package com.api.pet.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.pet.model.PetModel;

@Repository
public interface IPetRepository extends JpaRepository<PetModel, Long>{
  ArrayList<PetModel> findByCategory(String category);
}
