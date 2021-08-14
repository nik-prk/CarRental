package com.nyit.carrental.usermanagement.service;

import com.nyit.carrental.usermanagement.exception.UserException;

public interface UserServiceAll<T> {
	public T executeUserService() throws UserException;
}
