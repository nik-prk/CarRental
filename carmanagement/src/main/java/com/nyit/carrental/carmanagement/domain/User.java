package com.nyit.carrental.carmanagement.domain;

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
	private String documentId;
	private String avatarId;
	private boolean onGoing;
	private boolean accountVerified;
	private Set<Role> roles;

}
