package com.nyit.carrental.carmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.model.VehicleAmount;
import com.nyit.carrental.carmanagement.repo.VehicleTypeRepository;

@Service
public class GetAllVehicleTypeService implements VehicleServiceAll<List<VehicleAmount>>{
	
	@Autowired
	VehicleTypeRepository vehicleTypeRepository;

	@Override
	public List<VehicleAmount> executeVehicleService() throws VehicleException {
		return vehicleTypeRepository.findAll();
	}

}
