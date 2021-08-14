package com.nyit.carrental.carmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyit.carrental.carmanagement.domain.VehicleDTO;
import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.model.Vehicle;
import com.nyit.carrental.carmanagement.model.VehicleAmount;
import com.nyit.carrental.carmanagement.repo.VehicleRepository;
import com.nyit.carrental.carmanagement.repo.VehicleTypeRepository;
import com.nyit.carrental.carmanagement.util.VehicleUtil;

@Service
public class CreateVehicleService implements VehicleService<VehicleDTO, Vehicle> {

	@Autowired
	VehicleTypeRepository vehicleTypeRepository;

	@Autowired
	VehicleRepository vehicleRepository;

	@Override
	public Vehicle executeVehicleService(VehicleDTO req) throws VehicleException {
		Vehicle vehicle = null;
		if (null != req && req.getVehicleNumber() != null &&  !req.getVehicleNumber().isEmpty()) {
			VehicleUtil.checkIfValidType(req.getType());
			Vehicle duplicateVehicle = vehicleRepository.findByVehicleNumber(req.getVehicleNumber());
			if (null == duplicateVehicle) {
				VehicleAmount vehicleQuantity = vehicleTypeRepository.findByType(req.getType());
				if (null != vehicleQuantity) {
					ObjectMapper objectMapper = new ObjectMapper();
					vehicle = objectMapper.convertValue(req, Vehicle.class);
					vehicleRepository.save(vehicle);
				} else {
					throw new VehicleException(HttpStatus.NOT_FOUND.name(), "Vehicle Type doesnot Exists in DB");
				}
			} else {
				throw new VehicleException(HttpStatus.NOT_FOUND.name(), "Vehicle already Exists in DB");
			}
		}else {
			throw new VehicleException(HttpStatus.BAD_REQUEST.name(), "Vehicle number cannot be empty");
		}
		return vehicle;
	}

}
