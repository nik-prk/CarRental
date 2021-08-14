package com.nyit.carrental.usermanagement.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserWallet {
	
	private String id;
	private String userId;
	private double amount;

}

