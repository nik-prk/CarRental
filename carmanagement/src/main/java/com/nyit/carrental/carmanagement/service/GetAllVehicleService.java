package com.nyit.carrental.carmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.model.Vehicle;
import com.nyit.carrental.carmanagement.repo.VehicleRepository;

@Service
public class GetAllVehicleService implements VehicleServiceAll<List<Vehicle>>{
	
	@Autowired
	VehicleRepository vehicleRepository;

	@Override
	public List<Vehicle> executeVehicleService() throws VehicleException {
		return vehicleRepository.findAll();
	}

}
