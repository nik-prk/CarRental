package com.nyit.carrental.gateway.domain;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
	
	private String id;
	private String name;
	private String phoneNumber;
	private String email;
	private String password;
	private boolean accountVerified;
	private Set<Role> roles;

}
