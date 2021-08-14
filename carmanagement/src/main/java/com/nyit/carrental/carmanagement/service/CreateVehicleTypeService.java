package com.nyit.carrental.carmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyit.carrental.carmanagement.domain.VehicleAmountDTO;
import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.model.VehicleAmount;
import com.nyit.carrental.carmanagement.repo.VehicleTypeRepository;
import com.nyit.carrental.carmanagement.util.VehicleUtil;

@Service
public class CreateVehicleTypeService implements VehicleService<VehicleAmountDTO, VehicleAmount> {

	@Autowired
	VehicleTypeRepository vehicleTypeRepository;

	@Override
	public VehicleAmount executeVehicleService(VehicleAmountDTO req) throws VehicleException {
		VehicleAmount vehicleQuantity = null;
		if (null != req) {
			if (null != req.getType() && !req.getType().isEmpty()) {
				VehicleUtil.checkIfValidType(req.getType());
				VehicleAmount checkTypeExist = vehicleTypeRepository.findByType(req.getType());
				if (null == checkTypeExist) {
					ObjectMapper objectMapper = new ObjectMapper();
					vehicleQuantity = objectMapper.convertValue(req, VehicleAmount.class);
					vehicleTypeRepository.save(vehicleQuantity);
				} else {
					throw new VehicleException(HttpStatus.FOUND.name(), "Vehicle Type already exists");
				}
			} else {
				throw new VehicleException(HttpStatus.BAD_REQUEST.name(), "Vehicle Type cannot be  null");
			}
		}
		return vehicleQuantity;
	}

	

}
