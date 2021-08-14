package com.nyit.carrental.carmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.repo.VehicleRepository;

@Service
public class DeleteVehicleByIdService implements VehicleService<String, Boolean> {

	@Autowired
	VehicleRepository vehicleRepository;

	@Override
	public Boolean executeVehicleService(String req) throws VehicleException {
		if (!req.isEmpty()) {
			vehicleRepository.deleteById(req);
			return true;
		} else {
			throw new VehicleException(HttpStatus.BAD_REQUEST.name(), "Id cannot be null");
		}
	}

}
