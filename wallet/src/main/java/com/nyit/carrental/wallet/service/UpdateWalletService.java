package com.nyit.carrental.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nyit.carrental.wallet.exception.WalletException;
import com.nyit.carrental.wallet.model.UserWallet;
import com.nyit.carrental.wallet.repo.WalletRepository;

@Service
public class UpdateWalletService implements WalletService<UserWallet, UserWallet> {

	@Autowired
	WalletRepository walletRepository;

	@Override
	public UserWallet executeWalletService(UserWallet req) throws WalletException {
		if (req != null && !StringUtils.isEmpty(req.getUserId())) {
			UserWallet userWallet = walletRepository.findByUserId(req.getUserId());
			if (userWallet != null) {
				userWallet.setAmount(req.getAmount());
				return walletRepository.save(userWallet);
			} else {
				throw new WalletException(HttpStatus.BAD_REQUEST.name(), "User Wallet does not Exists with the id ");
			}
		} else {
			throw new WalletException(HttpStatus.CONFLICT.name(), "User Id is not provided ");
		}
	}

}
