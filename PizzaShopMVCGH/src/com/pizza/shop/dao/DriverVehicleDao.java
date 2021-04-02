package com.pizza.shop.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pizza.shop.dbConnection.DBConnection;
import com.pizza.shop.entity.DriverVehicle;
import com.pizza.shop.entity.Employee;

@Repository
public class DriverVehicleDao extends DBConnection implements DriverVehicleDaoI {

	@Override
	public boolean addCar(DriverVehicle car) {
		try {
			this.connect();
			em.getTransaction().begin();
			em.persist(car);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public DriverVehicle getCar(int dId) {
		this.connect();
		DriverVehicle carFound = em.find(DriverVehicle.class, dId);
		this.disconnect();
		return carFound;
	}

	@Override
	public boolean updateCar(DriverVehicle car) {
		try {
			this.connect();
			em.getTransaction().begin();
			DriverVehicle carFound = em.find(DriverVehicle.class, car.getdId());
			carFound.setModel(car.getModel());
			carFound.setYear(car.getYear());
			carFound.setColor(car.getColor());
			carFound.setDriverId(car.getDriverId());
			carFound.setInsuranceProvider(car.getInsuranceProvider());
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeCar(int dId) {
		try {
			this.connect();
			em.getTransaction().begin();
			DriverVehicle carFound = em.find(DriverVehicle.class, dId);
			em.remove(carFound);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<DriverVehicle> getAllCar() {
		this.connect();
		List<DriverVehicle> cars = em.createQuery("select d from DriverVehicle d").getResultList();
		this.disconnect();
		return cars;
	}

}
