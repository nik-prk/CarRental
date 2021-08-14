package com.nyit.carrental.carmanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nyit.carrental.carmanagement.domain.UserWallet;

@FeignClient(name = "wallet")
public interface UserWalletFeignClient {
	
	@RequestMapping(value = "/updateAmount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<UserWallet> updateAmountInWallet(@RequestBody UserWallet userWalletDTO);
	
	@RequestMapping(value = "/findWallet/{userId}", method = RequestMethod.GET)
	public UserWallet findWallet(@PathVariable("userId") String userId);
	
}

