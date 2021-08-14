package com.nyit.carrental.usermanagement.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nyit.carrental.usermanagement.model.UserDetails;

@Repository
public interface UserRepository extends MongoRepository<UserDetails, String> {

	UserDetails findByPhoneNumberOrEmail(String phoneNumber, String email);

	UserDetails findByEmail(String email);

}
