package com.nyit.carrental.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nyit.carrental.wallet.domain.UserWalletDTO;
import com.nyit.carrental.wallet.exception.WalletException;
import com.nyit.carrental.wallet.model.UserWallet;
import com.nyit.carrental.wallet.service.CreateWalletService;
import com.nyit.carrental.wallet.service.FindWalletByUserIdService;
import com.nyit.carrental.wallet.service.UpdateWalletService;

@RestController
public class WalletController {
	
	@Autowired
	CreateWalletService createWalletService;
	
	@Autowired
	UpdateWalletService updateWalletService;
	
	@Autowired
	FindWalletByUserIdService findWalletByUserIdService;
	
	@RequestMapping(value = "/addAmount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<UserWallet> addAmountToWallet(@RequestBody UserWalletDTO userWalletDTO) throws WalletException {
		return new ResponseEntity<>(createWalletService.executeWalletService(userWalletDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateAmount", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<UserWallet> updateAmountInWallet(@RequestBody UserWallet userWallet) throws WalletException {
		return new ResponseEntity<>(updateWalletService.executeWalletService(userWallet), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findWallet/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public UserWallet findWallet(@PathVariable("userId") String userId) throws WalletException {
		return findWalletByUserIdService.executeWalletService(userId);
	}

}
