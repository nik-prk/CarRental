package com.nyit.carrental.usermanagement.service;

import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyit.carrental.usermanagement.domain.UserDetailsDTO;
import com.nyit.carrental.usermanagement.exception.UserException;
import com.nyit.carrental.usermanagement.model.Role;
import com.nyit.carrental.usermanagement.model.UserDetails;
import com.nyit.carrental.usermanagement.repo.RoleRepository;
import com.nyit.carrental.usermanagement.repo.UserRepository;

@Service
public class CreateUserService implements UserService<UserDetailsDTO, UserDetails> {

	@Autowired
	UserRepository userRepository;

	@Autowired
	FindByPhoneOrEmailService findByPhoneOrEmailService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	EmailService emailService;

	@Override
	public UserDetails executeUserService(UserDetailsDTO req) throws UserException {
		UserDetails userDetails = null;
		if (req != null && !StringUtils.isEmpty(req.getEmail()) && !StringUtils.isEmpty(req.getPhoneNumber())) {
			try {
				UserDetails checkUserExists = findByPhoneOrEmailService.executeUserService(req);
				if (null == checkUserExists) {
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					userDetails = objectMapper.convertValue(req, UserDetails.class);
					userDetails.setEmail(userDetails.getEmail().toLowerCase());
					userDetails.setPassword(Base64.getEncoder().encodeToString(userDetails.getPassword().getBytes()));
					userDetails.setVerifiedCode(UUID.randomUUID().toString());
					Set<String> roles = req.getRoleTypes();
					List<Role> existingRoles = roleRepository.findAll();
					if (!existingRoles.isEmpty()) {
						List<String> existingRolesName = existingRoles.stream().map(role -> role.getRole())
								.collect(Collectors.toList());
						List<String> filterRoles = roles.stream().filter(role -> !existingRolesName.contains(role))
								.collect(Collectors.toList());
						if (filterRoles.isEmpty()) {
							Set<Role> createRoles = existingRoles.stream().filter(role -> roles.contains(role.getRole())).collect(Collectors.toSet());
							 userDetails.setRoles(createRoles);
						}else {
							throw new UserException(HttpStatus.BAD_REQUEST.name(), "User role doesnot exist :"+filterRoles);
						}
					}
					userRepository.save(userDetails);
					emailService.sendSimpleMessage(userDetails);
				} else {
					throw new UserException(HttpStatus.FORBIDDEN.name(), "User Already exists with phone or email");
				}
			} catch (Exception ex) {
				throw new UserException(HttpStatus.BAD_REQUEST.name(), ex.getMessage());
			}
		} else {
			throw new UserException(HttpStatus.BAD_REQUEST.name(), "Either Mobile or Email id is not provided");
		}
		return userDetails;
	}

}
