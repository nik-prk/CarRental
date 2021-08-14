package com.nyit.carrental.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nyit.carrental.wallet.exception.WalletException;
import com.nyit.carrental.wallet.model.UserWallet;
import com.nyit.carrental.wallet.repo.WalletRepository;

@Service
public class FindWalletByUserIdService implements WalletService<String, UserWallet>{
	
	@Autowired
	WalletRepository walletRepository;

	@Override
	public UserWallet executeWalletService(String req) throws WalletException {
		if(!StringUtils.isEmpty(req))
		return walletRepository.findByUserId(req);
		return null;
	}

}
