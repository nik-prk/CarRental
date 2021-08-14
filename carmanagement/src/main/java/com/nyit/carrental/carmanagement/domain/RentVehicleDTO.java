package com.nyit.carrental.carmanagement.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RentVehicleDTO {
	
	private String user;
	private String vehicleId;
	private String drivenBy;
	private String vehicleNumber;

}
