package com.nyit.carrental.usermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyit.carrental.usermanagement.exception.UserException;
import com.nyit.carrental.usermanagement.model.Role;
import com.nyit.carrental.usermanagement.repo.RoleRepository;

@Service
public class CreateRolesForUser implements UserService<Set<String>, List<Role>> {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> executeUserService(Set<String> req) throws UserException {
		List<Role> roles = new ArrayList<>();
		if (req != null && !req.isEmpty()) {
			List<Role> existingRoles = roleRepository.findAll();
			if (!existingRoles.isEmpty()) {
				List<String> existingRolesName = existingRoles.stream().map(role -> role.getRole())
						.collect(Collectors.toList());
				List<String> filterRoles = req.stream().filter(role -> !existingRolesName.contains(role))
						.collect(Collectors.toList());
				if (!filterRoles.isEmpty()) {
					filterRoles.forEach(role -> {
						Role roleM = new Role();
						roleM.setRole(role);
						roles.add(roleM);
					});
				}
			}else {
				req.forEach(role -> {
						Role roleM = new Role();
						roleM.setRole(role);
						roles.add(roleM);
					});
			}
			return roleRepository.saveAll(roles);
		}
		return roles;
	}

}
