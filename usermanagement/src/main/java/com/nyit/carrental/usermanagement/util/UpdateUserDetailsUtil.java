package com.nyit.carrental.usermanagement.util;

import java.util.Base64;

import org.springframework.util.StringUtils;

import com.nyit.carrental.usermanagement.domain.UpdateUserDetailsDTO;
import com.nyit.carrental.usermanagement.model.UserDetails;

public class UpdateUserDetailsUtil {

	public static UserDetails getUserDetails(UpdateUserDetailsDTO upDetailsDTO, UserDetails userDetails) {
		if(!StringUtils.isEmpty(upDetailsDTO.getAvatarId())) {
			userDetails.setAvatarId(upDetailsDTO.getAvatarId());
		}
		if(!StringUtils.isEmpty(upDetailsDTO.getDocumentId())) {
			userDetails.setDocumentId(upDetailsDTO.getDocumentId());
		}
		if(!StringUtils.isEmpty(upDetailsDTO.getPassword())) {
			userDetails.setEmail(Base64.getEncoder().encodeToString(upDetailsDTO.getPassword().getBytes()));
		}
		if(!StringUtils.isEmpty(upDetailsDTO.getName())) {
			userDetails.setName(upDetailsDTO.getName());
		}
		if(upDetailsDTO.isAccountVerified() == true) {
			userDetails.setAccountVerified(true);
		}
		return userDetails;
	}

}
