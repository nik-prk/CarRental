package com.nyit.carrental.wallet.exception;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public class WalletException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private final String errorCode;
	private final String errorMessage;
	

}
