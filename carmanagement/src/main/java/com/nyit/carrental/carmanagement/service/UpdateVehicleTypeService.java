package com.nyit.carrental.carmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.model.VehicleAmount;
import com.nyit.carrental.carmanagement.repo.VehicleTypeRepository;
import com.nyit.carrental.carmanagement.util.VehicleUtil;

@Service
public class UpdateVehicleTypeService implements VehicleService<VehicleAmount, VehicleAmount>{
	
	@Autowired
	VehicleTypeRepository vehicleTypeRepository;

	@Override
	public VehicleAmount executeVehicleService(VehicleAmount req) throws VehicleException {
		if(null != req && null != req.getId() && !req.getId().isEmpty()) {
			VehicleAmount vehicleQuantity = vehicleTypeRepository.findById(req.getId()).orElse(null);
			if(null != vehicleQuantity) {
				VehicleUtil.updateVehicleQuantity(req, vehicleQuantity);
				return vehicleTypeRepository.save(vehicleQuantity);
			}else {
				throw new VehicleException(HttpStatus.NOT_FOUND.name(), "Vehicle Type with this Id doesnot exists");
			}
		}else {
			throw new VehicleException(HttpStatus.BAD_REQUEST.name(), "Object Or Id Cannot be null");
		}
	}

}
