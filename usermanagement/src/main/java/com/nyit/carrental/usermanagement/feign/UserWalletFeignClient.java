package com.nyit.carrental.usermanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nyit.carrental.usermanagement.domain.UserWallet;

@FeignClient(name = "wallet")
public interface UserWalletFeignClient {
	
	@RequestMapping(value = "/addAmount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<UserWallet> addAmountToWallet(@RequestBody UserWallet userWalletDTO);
	
}

