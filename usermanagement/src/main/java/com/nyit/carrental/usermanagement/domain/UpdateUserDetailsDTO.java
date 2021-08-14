package com.nyit.carrental.usermanagement.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UpdateUserDetailsDTO {
	
	private String id;
	private String name;
	private String documentId;
	private String avatarId;
	private boolean accountVerified;
	private String password;
}
