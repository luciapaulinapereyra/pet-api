package com.example.demo.pet.app.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pet.app.security.entity.Role;
import com.example.demo.pet.app.security.enums.NameRole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByNameRole(NameRole nameRole);
}
