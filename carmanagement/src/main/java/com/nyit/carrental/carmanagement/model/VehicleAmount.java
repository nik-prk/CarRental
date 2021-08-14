package com.nyit.carrental.carmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document("vehiclequantity")
public class VehicleAmount {
	
	@Id
	public String id;
	public String type;
	public double rentAmount;

}
