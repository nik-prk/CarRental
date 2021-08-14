package com.nyit.carrental.carmanagement.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VehicleAmountDTO {

	public String type;
	public double rentAmount;

}
