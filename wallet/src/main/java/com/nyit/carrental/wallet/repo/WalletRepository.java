package com.nyit.carrental.wallet.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nyit.carrental.wallet.model.UserWallet;

public interface WalletRepository extends MongoRepository<UserWallet, String>{
	
	UserWallet findByUserId(String userId);

}
