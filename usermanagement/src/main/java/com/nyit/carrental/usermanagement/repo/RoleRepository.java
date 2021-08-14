package com.nyit.carrental.usermanagement.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nyit.carrental.usermanagement.model.Role;


public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}