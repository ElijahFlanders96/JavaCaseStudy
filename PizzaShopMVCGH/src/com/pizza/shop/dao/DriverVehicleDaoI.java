package com.pizza.shop.dao;

import java.util.List;

import com.pizza.shop.entity.DriverVehicle;

public interface DriverVehicleDaoI {
	public boolean addCar(DriverVehicle car);
	public DriverVehicle getCar(int dId);
	public boolean updateCar(DriverVehicle car);
	public boolean removeCar(int dId);
	public List<DriverVehicle> getAllCar();
}
