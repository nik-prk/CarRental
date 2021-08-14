package com.nyit.carrental.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nyit.carrental.gateway.domain.User;

@FeignClient(name = "user-management")
public interface UserFeignClient {
	
	@RequestMapping(value = "/findUser/{email}",method = RequestMethod.GET)
	public User findUserByEmail(@PathVariable("email") String email);
	
	/*@RequestMapping(value = "/createRole",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<Role>> createRole(@RequestBody Set<String> roles);*/
	
	/*@RequestMapping(value = "/role",method = RequestMethod.GET,headers = {"Accept" + "=" + "application/json"})
	public ResponseEntity<List<Role>> findAllRoles();*/
}

