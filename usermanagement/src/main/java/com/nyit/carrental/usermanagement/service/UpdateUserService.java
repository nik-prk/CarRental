package com.nyit.carrental.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nyit.carrental.usermanagement.domain.UpdateUserDetailsDTO;
import com.nyit.carrental.usermanagement.exception.UserException;
import com.nyit.carrental.usermanagement.model.UserDetails;
import com.nyit.carrental.usermanagement.repo.UserRepository;
import com.nyit.carrental.usermanagement.util.UpdateUserDetailsUtil;

@Service
public class UpdateUserService implements UserService<UpdateUserDetailsDTO, UserDetails>{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails executeUserService(UpdateUserDetailsDTO req) throws UserException {
		try {
		return (null!=req) ? updateUserDetails(req) : null;
		}catch (Exception e) {
			throw new UserException(HttpStatus.BAD_REQUEST.name(), e.getMessage());
		}
	}

	private UserDetails updateUserDetails(UpdateUserDetailsDTO req) throws UserException {
		if(req.getId() != null) {
			 UserDetails userDetails = userRepository.findById(req.getId()).orElse(null);
			 if(userDetails!=null) {
				 UpdateUserDetailsUtil.getUserDetails(req, userDetails);
				 return userRepository.save(userDetails); 
			 }else {
				 throw new UserException(HttpStatus.NOT_FOUND.name(), "User doest not exist with the given id");
			 }
		}else {
			 throw new UserException(HttpStatus.BAD_REQUEST.name(), "Id is mandatory for Update");
		 }
		
	}

}
