package com.nyit.carrental.carmanagement.service;

import com.nyit.carrental.carmanagement.exception.VehicleException;

public interface VehicleServiceAll<T> {
	
	public T executeVehicleService() throws VehicleException;

}
