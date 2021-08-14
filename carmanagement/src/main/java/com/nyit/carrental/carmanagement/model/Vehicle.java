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
@Document("vehicle")
public class Vehicle {
	
	@Id
	public String id;
	public String name;
	public String type;
	public long totalMileDriven;
	public String vehicleNumber;
	public String avatarId;
	public String documentId;
	public boolean booked;

}
