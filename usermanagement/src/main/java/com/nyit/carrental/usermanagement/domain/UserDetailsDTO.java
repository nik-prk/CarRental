package com.nyit.carrental.usermanagement.domain;

import java.util.Set;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDetailsDTO {
	
	private String name;
	private String phoneNumber;
	private String email;
	private String documentId;
	private String avatarId;
	private Set<String> roleTypes;
	private String password;
}
