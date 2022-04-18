package com.example.demo.pet.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pet.app.model.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long> {
    public Optional<UserModel> findByEmail(String email);
}
