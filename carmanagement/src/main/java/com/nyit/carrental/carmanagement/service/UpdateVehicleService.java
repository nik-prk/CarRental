package com.nyit.carrental.carmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.model.Vehicle;
import com.nyit.carrental.carmanagement.repo.VehicleRepository;
import com.nyit.carrental.carmanagement.util.VehicleUtil;

@Service
public class UpdateVehicleService implements VehicleService<Vehicle, Vehicle> {

	@Autowired
	VehicleRepository vehicleRepository;

	@Override
	public Vehicle executeVehicleService(Vehicle req) throws VehicleException {
		if (null != req && null != req.getId() && !req.getId().isEmpty()) {
			Vehicle vehicle = vehicleRepository.findById(req.getId()).orElse(null);
			if (null != vehicle) {
				VehicleUtil.updateVehicle(req, vehicle);
				return vehicleRepository.save(vehicle);
			} else {
				throw new VehicleException(HttpStatus.NOT_FOUND.name(), "Vehicle with this Id doesnot exists");
			}

		} else {
			throw new VehicleException(HttpStatus.BAD_REQUEST.name(), "Object Or Id Cannot be null");
		}
	}

}
