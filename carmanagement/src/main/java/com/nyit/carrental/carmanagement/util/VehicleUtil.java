package com.nyit.carrental.carmanagement.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.nyit.carrental.carmanagement.domain.VehicleType;
import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.model.Vehicle;
import com.nyit.carrental.carmanagement.model.VehicleAmount;

public class VehicleUtil {

	public static VehicleAmount updateVehicleQuantity(VehicleAmount requestVehicleQuantity,
			VehicleAmount vehicleQuantity) {
		if (requestVehicleQuantity.getRentAmount() > 0.0) {
			vehicleQuantity.setRentAmount(requestVehicleQuantity.getRentAmount());
		}
		return vehicleQuantity;
	}

	public static boolean checkIfValidType(String type) throws VehicleException {
		if (VehicleType.valueOf(type) != null) {
			return true;
		} else {
			throw new VehicleException(HttpStatus.NOT_FOUND.name(), "Vehicle Type doesnot Exists");
		}
	}

	public static Vehicle updateVehicle(Vehicle requestVehicle, Vehicle vehicle) {

		if (null != requestVehicle.getName() && !requestVehicle.getName().isEmpty()) {
			vehicle.setName(requestVehicle.getName());
		}
		if (null != requestVehicle.getAvatarId() && !requestVehicle.getAvatarId().isEmpty()) {
			vehicle.setAvatarId(requestVehicle.getAvatarId());
		}
		if (null != requestVehicle.getDocumentId() && !requestVehicle.getDocumentId().isEmpty()) {
			vehicle.setDocumentId(requestVehicle.getDocumentId());
		}
		if (vehicle.getTotalMileDriven() > requestVehicle.getTotalMileDriven()) {
			vehicle.setTotalMileDriven(requestVehicle.getTotalMileDriven());
		}
		return vehicle;
	}

	public static String getTime(Date date) {
		String dateString = null;
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			dateString = sdf.format(date);
		}
		return dateString;
	}
	
	public static long getTimeDifference(String startDate, String endDate) throws VehicleException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		long diffHours = 0;
		try {
			d1 = format.parse(startDate);
			d2 = format.parse(endDate);
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
			//long diffSeconds = diff / 1000 % 60;
			//long diffMinutes = diff / (60 * 1000) % 60;
			diffHours = diff / (60 * 60 * 1000) % 24;
			//long diffDays = diff / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			throw new VehicleException(HttpStatus.BAD_REQUEST.name(), e.getMessage());
		}
		return diffHours;
	}
	
	public static double getAmount(long time, String driver) {
		double billAmount = 0.0;
		if(!StringUtils.isEmpty(driver)) {
			if(time<=1) {
				billAmount = 10;
			}else if(time > 1 && time <= 3) {
				billAmount = 25;
			}else if(time > 3 && time <= 10) {
				billAmount = 80;
			}else if(time >10 && time <= 20) {
				billAmount = 150;
			}else if(time >20 && time <=24) {
				billAmount = 175;
			}else {
				billAmount = 500;
			}
		}else {
			if(time<=1) {
				billAmount = 7;
			}else if(time > 1 && time <= 3) {
				billAmount = 18;
			}else if(time > 3 && time <= 10) {
				billAmount = 55;
			}else if(time >10 && time <= 20) {
				billAmount = 110;
			}else if(time >20 && time <=24) {
				billAmount = 130;
			}else {
				billAmount = 300;
			}
		}
		return billAmount;
		
	}
}
