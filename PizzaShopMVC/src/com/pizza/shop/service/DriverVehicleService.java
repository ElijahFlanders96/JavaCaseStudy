package com.pizza.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizza.shop.dao.DriverVehicleDao;
import com.pizza.shop.entity.DriverVehicle;

@Service
public class DriverVehicleService {
	
	private DriverVehicleDao carD = new DriverVehicleDao();
	
	@Autowired
	public DriverVehicleService(DriverVehicleDao carD) {
		this.carD = carD;
	}
	
	public void addCarService(DriverVehicle car) {
		carD.addCar(car);
	}
	public DriverVehicle getCarService(int dId) {
		return carD.getCar(dId);
	}
	public void updateCarService(DriverVehicle car) {
		carD.updateCar(car);
	}
	public List<DriverVehicle> getAllCarService() {
		return carD.getAllCar();
	}
	public void removeCarService(int dId) {
		carD.removeCar(dId);
	}
	public boolean validateCarService(int dId, String model, int year, String color, String insuranceProvider, int driverId) {
		DriverVehicle carFound = carD.getCar(dId);
		if (carFound!=null) {
			if (carFound.getModel().equals(model) && carFound.getYear() == year && carFound.getColor().equals(color) && carFound.getInsuranceProvider().equals(insuranceProvider) && carFound.getDriverId() == driverId) {
				return true;
			}
		}
		return false;
	}
}
