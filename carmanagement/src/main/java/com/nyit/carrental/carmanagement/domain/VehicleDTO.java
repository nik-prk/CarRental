package com.nyit.carrental.carmanagement.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class VehicleDTO {
	
	public String name;
	public String type;
	public long totalMileDriven;
	public String vehicleNumber;
	public String avatarId;
	public String documentId;
	
}
