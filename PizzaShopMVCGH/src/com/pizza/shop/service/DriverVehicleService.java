package com.pizza.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizza.shop.dao.DriverVehicleDao;
import com.pizza.shop.entity.DriverVehicle;

//This is the service from the DriverVehicleDao
//These methods are called in the HomeController instead of the dao methods themselves
@Service
public class DriverVehicleService {
	
	private DriverVehicleDao carD = new DriverVehicleDao();
	
	@Autowired
	public DriverVehicleService(DriverVehicleDao carD) {
		this.carD = carD;
	}
	
	// Calls the Add Vehicle method from the dao
	public void addCarService(DriverVehicle car) {
		carD.addCar(car);
	}
	// Calls the Get Vehicle method from the dao
	public DriverVehicle getCarService(int dId) {
		return carD.getCar(dId);
	}
	// Calls the Update Vehicle method from the dao
	public void updateCarService(DriverVehicle car) {
		carD.updateCar(car);
	}
	// Calls the Get All Vehicles method from the dao
	public List<DriverVehicle> getAllCarService() {
		return carD.getAllCar();
	}
	// Calls the Remove Vehicle method from the dao
	public void removeCarService(int dId) {
		carD.removeCar(dId);
	}
}
