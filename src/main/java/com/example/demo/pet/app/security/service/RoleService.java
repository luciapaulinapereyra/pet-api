package com.example.demo.pet.app.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pet.app.security.entity.Role;
import com.example.demo.pet.app.security.enums.NameRole;
import com.example.demo.pet.app.security.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByNameRole(NameRole nameRole){
        return roleRepository.findByNameRole(nameRole);
    }

    public void save(Role role){
        roleRepository.save(role);
    }
}
