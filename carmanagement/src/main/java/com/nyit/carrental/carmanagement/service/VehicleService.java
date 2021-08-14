package com.nyit.carrental.carmanagement.service;

import com.nyit.carrental.carmanagement.exception.VehicleException;

public interface VehicleService<S,T> {
	
	public T executeVehicleService(S req) throws VehicleException;

}
