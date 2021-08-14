package com.nyit.carrental.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyit.carrental.wallet.domain.UserWalletDTO;
import com.nyit.carrental.wallet.exception.WalletException;
import com.nyit.carrental.wallet.model.UserWallet;
import com.nyit.carrental.wallet.repo.WalletRepository;

@Service
public class CreateWalletService implements WalletService<UserWalletDTO, UserWallet>{
	
	@Autowired
	WalletRepository walletRepository;

	@Override
	public UserWallet executeWalletService(UserWalletDTO req) throws WalletException {
		UserWallet userWallet = null;
		if(req != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			userWallet = objectMapper.convertValue(req, UserWallet.class);
			walletRepository.save(userWallet);
		}
		return userWallet;
	}

}
