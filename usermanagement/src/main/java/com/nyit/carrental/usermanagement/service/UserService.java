package com.nyit.carrental.usermanagement.service;

import com.nyit.carrental.usermanagement.exception.UserException;

public interface UserService<S,T> {
	
	public T executeUserService(S req) throws UserException;

}
