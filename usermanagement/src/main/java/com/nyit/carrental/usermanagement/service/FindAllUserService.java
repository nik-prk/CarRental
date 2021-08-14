package com.nyit.carrental.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nyit.carrental.usermanagement.exception.UserException;
import com.nyit.carrental.usermanagement.model.UserDetails;
import com.nyit.carrental.usermanagement.repo.UserRepository;

@Service
public class FindAllUserService implements UserServiceAll<List<UserDetails>>{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserDetails> executeUserService() throws UserException {
		try {
		return userRepository.findAll();
		}catch (Exception e) {
			throw new UserException(HttpStatus.BAD_REQUEST.name(), e.getMessage());
		}
	}

}
