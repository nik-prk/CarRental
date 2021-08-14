package com.nyit.carrental.usermanagement.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nyit.carrental.usermanagement.domain.UpdateStatusDTO;
import com.nyit.carrental.usermanagement.domain.UpdateUserDetailsDTO;
import com.nyit.carrental.usermanagement.domain.UserDetailsDTO;
import com.nyit.carrental.usermanagement.exception.UserException;
import com.nyit.carrental.usermanagement.model.Role;
import com.nyit.carrental.usermanagement.model.UserDetails;
import com.nyit.carrental.usermanagement.repo.UserRepository;
import com.nyit.carrental.usermanagement.service.CreateRolesForUser;
import com.nyit.carrental.usermanagement.service.CreateUserService;
import com.nyit.carrental.usermanagement.service.DeleteUserService;
import com.nyit.carrental.usermanagement.service.EmailService;
import com.nyit.carrental.usermanagement.service.FindAllRolesService;
import com.nyit.carrental.usermanagement.service.FindAllUserService;
import com.nyit.carrental.usermanagement.service.FindByPhoneOrEmailService;
import com.nyit.carrental.usermanagement.service.UpdateUserService;
import com.nyit.carrental.usermanagement.service.UpdateUserStatusByEmailId;

@RestController
public class UserController {
	@Autowired
	CreateUserService createUserService;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UpdateUserService updateUserService;
	
	@Autowired
	FindAllUserService findAllUserService;
	
	@Autowired
	DeleteUserService deleteUserService;
	
	@Autowired
	FindByPhoneOrEmailService findByPhoneOrEmailService;
	
	@Autowired
	CreateRolesForUser createRolesForUser;
	
	@Autowired
	FindAllRolesService findAllRolesService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	UpdateUserStatusByEmailId updateUserStatusByEmailId;
	@RequestMapping(value = "/registerUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<UserDetails> createUser(@RequestBody UserDetailsDTO userDetailsDTO) throws UserException {
		return new ResponseEntity<>(createUserService.executeUserService(userDetailsDTO), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<UserDetails> updateUser(@RequestBody UpdateUserDetailsDTO updateUserDetailsDTO) throws UserException {
		return new ResponseEntity<>(updateUserService.executeUserService(updateUserDetailsDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<UserDetails>> findAllUser() throws UserException {
		return new ResponseEntity<>(findAllUserService.executeUserService(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") String id) throws UserException {
		return new ResponseEntity<>(deleteUserService.executeUserService(id), HttpStatus.OK);
	}
	
	//@Cacheable(value="user", key="#email", unless = "#result == null")
	@RequestMapping(value = "/findUser/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDetails findUserByEmail(@PathVariable("email") String email) throws UserException {
		UserDetailsDTO userDetailsDTO =  new UserDetailsDTO();
		userDetailsDTO.setEmail(email);
		return findByPhoneOrEmailService.executeUserService(userDetailsDTO);
	}
	
	@RequestMapping(value = "/createRole", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<List<Role>> createRole(@RequestBody Set<String> roles) throws UserException {
		return new ResponseEntity<>(createRolesForUser.executeUserService(roles), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getAllRoles", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<Role>> findAllRoles() throws UserException {
		return new ResponseEntity<>(findAllRolesService.executeUserService(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/status/{email}/{status}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<UserDetails> updateBookingStatus(@PathVariable("email") String email, @PathVariable("status") boolean status) throws UserException {
		UpdateStatusDTO updateStatusDTO = new UpdateStatusDTO();
		updateStatusDTO.setEmail(email);
		updateStatusDTO.setOnGoing(status);
		return new ResponseEntity<>(updateUserStatusByEmailId.executeUserService(updateStatusDTO), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/account/{email}/{code}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<UserDetails> updateAccountStatus(@PathVariable("email") String email, @PathVariable("code") String code) throws UserException {
		UpdateStatusDTO updateStatusDTO = new UpdateStatusDTO();
		updateStatusDTO.setEmail(email);
		updateStatusDTO.setVerifiedCode(code);
		return new ResponseEntity<>(updateUserStatusByEmailId.executeUserService(updateStatusDTO), HttpStatus.OK);
	}
	
}
