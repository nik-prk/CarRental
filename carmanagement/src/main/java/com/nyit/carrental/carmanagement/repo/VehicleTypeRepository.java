package com.nyit.carrental.carmanagement.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nyit.carrental.carmanagement.model.VehicleAmount;

@Repository
public interface VehicleTypeRepository extends MongoRepository<VehicleAmount, String>{
	
	public VehicleAmount findByType(String type);

}
