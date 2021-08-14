package com.nyit.carrental.gateway.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nyit.carrental.gateway.domain.Role;
import com.nyit.carrental.gateway.domain.User;
import com.nyit.carrental.gateway.feign.UserFeignClient;

@Service
public class GetUserDetails implements UserDetailsService {

	@Autowired
	UserFeignClient userFeignClient;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//RestTemplate restTemplate = new RestTemplate();
		//User user = restTemplate.getForObject("http://localhost:8003/user/"+email, User.class);
		User user = userFeignClient.findUserByEmail(email);
		if (user != null && user.isAccountVerified()) {
			//user.getBody().setPassword(Base64.getDecoder().decode(user.getBody().getPassword()).toString());
			List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
			return buildUserForAuthentication(user, authorities);
		} else {
			throw new UsernameNotFoundException("username not found");
		}
	}

	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
		Set<GrantedAuthority> roles = new HashSet<>();
		userRoles.forEach((role) -> {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		});

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

		return grantedAuthorities;
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
}
