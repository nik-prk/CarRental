package com.nyit.carrental.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nyit.carrental.usermanagement.domain.UserDetailsDTO;
import com.nyit.carrental.usermanagement.exception.UserException;
import com.nyit.carrental.usermanagement.model.UserDetails;
import com.nyit.carrental.usermanagement.repo.UserRepository;

@Service
public class FindByPhoneOrEmailService implements UserService<UserDetailsDTO, UserDetails>{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails executeUserService(UserDetailsDTO req) throws UserException {
		try {
		return userRepository.findByPhoneNumberOrEmail(req.getPhoneNumber(),req.getEmail().toLowerCase());
		}catch (Exception e) {
			throw new UserException(HttpStatus.BAD_REQUEST.name(), e.getMessage());
		}
	}

}
