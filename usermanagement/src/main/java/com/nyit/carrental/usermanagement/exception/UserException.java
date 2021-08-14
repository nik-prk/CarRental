package com.nyit.carrental.usermanagement.exception;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public class UserException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private final String errorCode;
	private final String errorMessage;
	

}
