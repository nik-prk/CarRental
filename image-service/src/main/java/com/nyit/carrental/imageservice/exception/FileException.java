package com.nyit.carrental.imageservice.exception;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public class FileException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private final String errorCode;
	private final String errorMessage;

}
