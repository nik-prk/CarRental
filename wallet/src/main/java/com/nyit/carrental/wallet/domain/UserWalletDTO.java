package com.nyit.carrental.wallet.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserWalletDTO {
	
	private String userId;
	private double amount;

}
