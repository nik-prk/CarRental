package com.nyit.carrental.carmanagement.service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nyit.carrental.carmanagement.domain.RentVehicleDTO;
import com.nyit.carrental.carmanagement.domain.User;
import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.feign.UserFeignClient;
import com.nyit.carrental.carmanagement.model.RentVehicle;
import com.nyit.carrental.carmanagement.model.Vehicle;
import com.nyit.carrental.carmanagement.repo.RentVehicleRepository;
import com.nyit.carrental.carmanagement.repo.VehicleRepository;
import com.nyit.carrental.carmanagement.util.VehicleUtil;

@Service
public class RentVehicleService implements VehicleService<RentVehicleDTO, RentVehicle> {

	@Autowired
	RentVehicleRepository rentVehicleRepository;

	@Autowired
	VehicleRepository vehicleRepository;

	@Autowired
	UserFeignClient userFeignClient;

	@Override
	public RentVehicle executeVehicleService(RentVehicleDTO req) throws VehicleException {
		RentVehicle rentVehicle = null;
		if (null != req && req.getVehicleId() != null && !req.getVehicleId().isEmpty()) {
			User user = userFeignClient.findUserByEmail(req.getUser());
			if(user == null) {
				throw new VehicleException(HttpStatus.BAD_REQUEST.name(), "User does not Exists");
			}
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			rentVehicle = objectMapper.convertValue(req, RentVehicle.class);
			if (null != req.getDrivenBy() && !req.getDrivenBy().isEmpty()) {
				// RestTemplate restTemplate = new RestTemplate();
				// User driver = restTemplate.getForObject("http://localhost:8083/findByEmail/"
				// + req.getDrivenBy(),User.class);
				User driver = userFeignClient.findUserByEmail(req.getDrivenBy());
				if (driver != null && !driver.isOnGoing()) {
					Set<String> roles = driver.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toSet());
					if (roles.contains("DRIVER")) {
						rentVehicle.setDrivenBy(driver.getEmail());
					} else {
						throw new VehicleException(HttpStatus.BAD_REQUEST.name(), "This user doesnot have Driver Role");
					}
				} else {
					throw new VehicleException(HttpStatus.BAD_REQUEST.name(),
							"Driver doesnot exists or already booked with this eamil");
				}
			}
			rentVehicle.setStartTime(VehicleUtil.getTime(new Date()));
			Vehicle vehicle = vehicleRepository.findById(req.getVehicleId()).orElse(null);
			if (null != vehicle) {
				if (vehicle.isBooked()) {
					throw new VehicleException(HttpStatus.CONFLICT.name(), "Vehicle is already booked");
				}
				vehicle.setBooked(true);
			} else {
				throw new VehicleException(HttpStatus.BAD_REQUEST.name(), "Vehicle doesnot exist with this Id");
			}
			rentVehicleRepository.save(rentVehicle);
			vehicleRepository.save(vehicle);
			if (null != req.getDrivenBy() && !req.getDrivenBy().isEmpty()) {
				userFeignClient.updateBookingStatus(req.getDrivenBy(), true);
			}
		} else {
			throw new VehicleException(HttpStatus.BAD_REQUEST.name(), "Vehicle id cannot be null");
		}
		return rentVehicle;
	}

}
