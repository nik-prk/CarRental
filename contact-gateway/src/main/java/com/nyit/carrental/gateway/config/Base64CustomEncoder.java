package com.nyit.carrental.gateway.config;

import java.util.Base64;

import org.springframework.security.crypto.password.PasswordEncoder;
 

public class Base64CustomEncoder implements PasswordEncoder {
 
    @Override
    public String encode(CharSequence rawPassword) {
    	String pass = rawPassword.toString();
        String hashed = Base64.getEncoder().encodeToString(pass.getBytes());
        return hashed;
    }
 
	@Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
    	String pass = rawPassword.toString();
    	String hash = Base64.getEncoder().encodeToString(pass.getBytes());
        return hash.equals(encodedPassword);
    }
 
}
