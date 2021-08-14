package com.nyit.carrental.carmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nyit.carrental.carmanagement.domain.VehicleDTO;
import com.nyit.carrental.carmanagement.domain.RentVehicleDTO;
import com.nyit.carrental.carmanagement.domain.VehicleAmountDTO;
import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.model.Bill;
import com.nyit.carrental.carmanagement.model.RentVehicle;
import com.nyit.carrental.carmanagement.model.Vehicle;
import com.nyit.carrental.carmanagement.model.VehicleAmount;
import com.nyit.carrental.carmanagement.service.CreateVehicleService;
import com.nyit.carrental.carmanagement.service.CreateVehicleTypeService;
import com.nyit.carrental.carmanagement.service.DeleteVehicleByIdService;
import com.nyit.carrental.carmanagement.service.DeleteVehicleTypeService;
import com.nyit.carrental.carmanagement.service.EndRentVehicleService;
import com.nyit.carrental.carmanagement.service.GetAllVehicleService;
import com.nyit.carrental.carmanagement.service.GetAllVehicleTypeService;
import com.nyit.carrental.carmanagement.service.RentVehicleService;
import com.nyit.carrental.carmanagement.service.UpdateVehicleService;
import com.nyit.carrental.carmanagement.service.UpdateVehicleTypeService;

@RestController
public class VehicleController {

	@Autowired
	CreateVehicleTypeService createVehicleTypeService;

	@Autowired
	UpdateVehicleTypeService updateVehicleTypeService;

	@Autowired
	GetAllVehicleTypeService getAllVehicleTypeService;

	@Autowired
	DeleteVehicleTypeService deleteVehicleTypeService;

	@Autowired
	CreateVehicleService createVehicleService;

	@Autowired
	UpdateVehicleService updateVehicleService;

	@Autowired
	GetAllVehicleService getAllVehicleService;

	@Autowired
	DeleteVehicleByIdService deleteVehicleByIdService;
	
	@Autowired
	RentVehicleService rentVehicleService;
	
	@Autowired
	EndRentVehicleService endRentVehicleService;

	@RequestMapping(value = "/createVehicleType", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<VehicleAmount> createVehicleType(@RequestBody VehicleAmountDTO vehicleQuantityDTO)
			throws VehicleException {
		return new ResponseEntity<>(createVehicleTypeService.executeVehicleService(vehicleQuantityDTO),
				HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateVehicleType", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<VehicleAmount> updateVehicleType(@RequestBody VehicleAmount vehicleQuantity)
			throws VehicleException {
		return new ResponseEntity<>(updateVehicleTypeService.executeVehicleService(vehicleQuantity), HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllVehicleTypes", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<VehicleAmount>> getAllVehicleType() throws VehicleException {
		return new ResponseEntity<>(getAllVehicleTypeService.executeVehicleService(), HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteVehicleType/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteVehicleTypeById(@PathVariable("id") String id) throws VehicleException {
		return new ResponseEntity<>(deleteVehicleTypeService.executeVehicleService(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/createVehicle", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Vehicle> createVehicle(@RequestBody VehicleDTO vehicleDTO) throws VehicleException {
		return new ResponseEntity<>(createVehicleService.executeVehicleService(vehicleDTO), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/updateVehicle", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle) throws VehicleException {
		return new ResponseEntity<>(updateVehicleService.executeVehicleService(vehicle), HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllVehicles", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<Vehicle>> getAllVehicle() throws VehicleException {
		return new ResponseEntity<>(getAllVehicleService.executeVehicleService(), HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteVehicleById(@PathVariable("id") String id) throws VehicleException {
		return new ResponseEntity<>(deleteVehicleByIdService.executeVehicleService(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rentVehicle", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<RentVehicle> rentVehicle(@RequestBody RentVehicleDTO rentVehicleDTO) throws VehicleException {
		return new ResponseEntity<>(rentVehicleService.executeVehicleService(rentVehicleDTO), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/endRental/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<Bill> rentVehicle(@PathVariable("id") String id) throws VehicleException {
		return new ResponseEntity<>(endRentVehicleService.executeVehicleService(id), HttpStatus.CREATED);
	}

}
