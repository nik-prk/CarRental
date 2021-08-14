package com.nyit.carrental.carmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document("bill")
public class Bill {
	
	@Id
	private String id;
	private String user;
	private String billGenerationTime;
	private double billAmount;
	private double cashTobePaid;
	private String driverName;
	private String vehicleType;
	private long totalTimeRented;
	private String vehicleNumber;
}
