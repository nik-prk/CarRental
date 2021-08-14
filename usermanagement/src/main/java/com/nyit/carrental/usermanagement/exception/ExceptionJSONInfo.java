package com.nyit.carrental.usermanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ExceptionJSONInfo {
	private final String statusCode;
	private final String reason;
	
	
}
