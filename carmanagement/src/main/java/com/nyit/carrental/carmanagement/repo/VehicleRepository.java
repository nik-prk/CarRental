package com.nyit.carrental.carmanagement.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nyit.carrental.carmanagement.model.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String>{

	Vehicle findByVehicleNumber(String vehicleNumber);

}
