package com.nyit.carrental.usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nyit.carrental.usermanagement.domain.UpdateStatusDTO;
import com.nyit.carrental.usermanagement.domain.UserWallet;
import com.nyit.carrental.usermanagement.exception.UserException;
import com.nyit.carrental.usermanagement.feign.UserWalletFeignClient;
import com.nyit.carrental.usermanagement.model.UserDetails;
import com.nyit.carrental.usermanagement.repo.UserRepository;

@Service
public class UpdateUserStatusByEmailId implements UserService<UpdateStatusDTO, UserDetails>{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserWalletFeignClient userWalletFeignClient;
	
	@Override
	public UserDetails executeUserService(UpdateStatusDTO req) throws UserException {
		UserDetails userDetails = null;
		if(req !=null) {
		if((null == req.getId() || req.getId().isEmpty()) && (null == req.getEmail() || req.getEmail().isEmpty())) {
			throw new UserException(HttpStatus.BAD_REQUEST.name(), "Id and email both are missing");
		}
		if(req.getId() !=null) {
			userDetails = userRepository.findById(req.getId()).orElse(null);
		}else {
			userDetails = userRepository.findByEmail(req.getEmail());
		}
		if(userDetails != null) {
			userDetails.setOnGoing(req.isOnGoing());
			if(req.getVerifiedCode() !=null && !req.getVerifiedCode().isEmpty() && req.getVerifiedCode().equals(userDetails.getVerifiedCode())) {
				userDetails.setAccountVerified(true);
				UserWallet userWallet = new UserWallet();
				userWallet.setUserId(userDetails.getEmail());
				userWalletFeignClient.addAmountToWallet(userWallet);
			}
			userRepository.save(userDetails);
		}
		}
		return userDetails;
	}

}
