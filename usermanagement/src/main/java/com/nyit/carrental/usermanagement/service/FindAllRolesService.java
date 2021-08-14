package com.nyit.carrental.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyit.carrental.usermanagement.exception.UserException;
import com.nyit.carrental.usermanagement.model.Role;
import com.nyit.carrental.usermanagement.repo.RoleRepository;

@Service
public class FindAllRolesService implements UserServiceAll<List<Role>>{
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> executeUserService() throws UserException {
		
		return roleRepository.findAll();
	}
	
	

}
