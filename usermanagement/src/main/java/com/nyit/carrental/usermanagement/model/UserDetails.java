package com.nyit.carrental.usermanagement.model;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "users")
public class UserDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;
	private String phoneNumber;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String email;
	private String password;
	private String documentId;
	private String avatarId;
	private boolean onGoing;
	private String verifiedCode;
	private boolean accountVerified;
	@DBRef
	private Set<Role> roles;

}
