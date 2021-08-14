package com.nyit.carrental.carmanagement.exception;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public class VehicleException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private final String errorCode;
	private final String errorMessage;
	

}
