package com.nyit.carrental.carmanagement.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nyit.carrental.carmanagement.domain.UserWallet;
import com.nyit.carrental.carmanagement.exception.VehicleException;
import com.nyit.carrental.carmanagement.feign.UserFeignClient;
import com.nyit.carrental.carmanagement.feign.UserWalletFeignClient;
import com.nyit.carrental.carmanagement.model.Bill;
import com.nyit.carrental.carmanagement.model.RentVehicle;
import com.nyit.carrental.carmanagement.model.Vehicle;
import com.nyit.carrental.carmanagement.repo.BillRepository;
import com.nyit.carrental.carmanagement.repo.RentVehicleRepository;
import com.nyit.carrental.carmanagement.repo.VehicleRepository;
import com.nyit.carrental.carmanagement.util.VehicleUtil;

@Service
public class EndRentVehicleService implements VehicleService<String, Bill> {

	@Autowired
	RentVehicleRepository rentVehicleRepository;

	@Autowired
	VehicleRepository vehicleRepository;

	@Autowired
	BillRepository billRepository;

	@Autowired
	UserFeignClient userFeignClient;

	@Autowired
	UserWalletFeignClient userWalletFeignClient;

	@Override
	public Bill executeVehicleService(String req) throws VehicleException {
		Bill bill = null;
		if (null != req && !req.isEmpty()) {
			RentVehicle rentVehicle = rentVehicleRepository.findById(req).orElse(null);
			if (null != rentVehicle) {
				rentVehicle.setEndTime(VehicleUtil.getTime(new Date()));
				Vehicle vehicle = vehicleRepository.findById(rentVehicle.getVehicleId()).orElse(null);
				if (null != vehicle) {
					vehicle.setBooked(false);
				}
				long totaltimerented = VehicleUtil.getTimeDifference(rentVehicle.getStartTime(),
						rentVehicle.getEndTime());
				double amount = VehicleUtil.getAmount(totaltimerented, rentVehicle.getDrivenBy());
				UserWallet userWallet = userWalletFeignClient.findWallet(rentVehicle.getUser());
				bill = new Bill();
				if (userWallet.getAmount() < amount) {
					bill.setCashTobePaid(amount - userWallet.getAmount());
					userWallet.setAmount(0);
				} else {
					userWallet.setAmount(userWallet.getAmount() - amount);
				}
				userWalletFeignClient.updateAmountInWallet(userWallet);
				bill.setBillAmount(amount);
				bill.setBillGenerationTime(VehicleUtil.getTime(new Date()));
				bill.setDriverName(rentVehicle.getDrivenBy());
				bill.setTotalTimeRented(totaltimerented);
				bill.setUser(rentVehicle.getUser());
				bill.setVehicleType(vehicle.getType());
				bill.setVehicleNumber(vehicle.getVehicleNumber());
				billRepository.save(bill);
				vehicleRepository.save(vehicle);
				rentVehicleRepository.save(rentVehicle);
				if (rentVehicle.getDrivenBy() != null && !rentVehicle.getDrivenBy().isEmpty()) {
					userFeignClient.updateBookingStatus(rentVehicle.getDrivenBy(), false);
				}
			} else {
				throw new VehicleException(HttpStatus.BAD_REQUEST.name(), "Rented vehicle not found with this Id");
			}
		}
		return bill;
	}

}
