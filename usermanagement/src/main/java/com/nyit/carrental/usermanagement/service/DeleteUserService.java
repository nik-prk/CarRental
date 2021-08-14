package com.nyit.carrental.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nyit.carrental.usermanagement.exception.UserException;
import com.nyit.carrental.usermanagement.repo.UserRepository;

@Service
public class DeleteUserService implements UserService<String, Boolean>{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Boolean executeUserService(String req) throws UserException {
		try {
		userRepository.deleteById(req);
		}catch (Exception e) {
			throw new UserException(HttpStatus.BAD_REQUEST.name(), e.getMessage());
		}
		return true;
	}

}
