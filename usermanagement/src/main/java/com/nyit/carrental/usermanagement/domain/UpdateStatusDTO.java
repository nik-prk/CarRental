package com.nyit.carrental.usermanagement.domain;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UpdateStatusDTO {
	
	private String id;
	private String email;
	private boolean onGoing;
	private String verifiedCode;	

}
