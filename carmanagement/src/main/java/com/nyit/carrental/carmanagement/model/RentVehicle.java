package com.nyit.carrental.carmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document("rentvehicle")
public class RentVehicle {
	
	@Id
	private String id;
	private String user;
	private String vehicleId;
	private String drivenBy;
	private String startTime;
	private String endTime;	

}
