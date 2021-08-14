package com.nyit.carrental.carmanagement.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nyit.carrental.carmanagement.model.RentVehicle;

@Repository
public interface RentVehicleRepository extends MongoRepository<RentVehicle, String>{

}
