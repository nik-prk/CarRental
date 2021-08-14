package com.nyit.carrental.carmanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nyit.carrental.carmanagement.domain.User;


@FeignClient(name= "user-management")
public interface UserFeignClient {
	
	@RequestMapping(value = "/findUser/{email}",method = RequestMethod.GET)
	public User findUserByEmail(@PathVariable("email") String email);
	
	@RequestMapping(value = "/user/status/{email}/{status}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<User> updateBookingStatus(@PathVariable("email") String email, @PathVariable("status") boolean status);
	
}

