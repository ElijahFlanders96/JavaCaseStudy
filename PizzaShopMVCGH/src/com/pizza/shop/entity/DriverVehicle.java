package com.pizza.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="DriverVehicle")
@Table(name="DriverVehicle")
public class DriverVehicle {
	@Id
	@Column
	private int dId;
	@Column
	private String model;
	@Column
	private int year;
	@Column
	private String color;
	@Column
	private String insuranceProvider;
	@Column
	private int driverId;
	
	public DriverVehicle() {
		super();
	}

	public DriverVehicle(int dId, String model, int year, String color, String insuranceProvider, int driverId) {
		super();
		this.dId = dId;
		this.model = model;
		this.year = year;
		this.color = color;
		this.insuranceProvider = insuranceProvider;
		this.driverId = driverId;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	
	@Override
	public String toString() {
		// need to fill out the first argument, need to figure out how and don't want to rn
		return String.format("", dId, model, year, color, insuranceProvider, driverId);
	}
	
	@Override
	public boolean equals(Object o) {
		DriverVehicle comparedTo = (DriverVehicle) o;
		if (this.dId==comparedTo.getdId()
			&& this.model.equals(comparedTo.getModel())
			&& this.year==comparedTo.getYear()
			&& this.color.equals(comparedTo.getColor())
			&& this.insuranceProvider.equals(comparedTo.getInsuranceProvider())
			&& this.driverId==comparedTo.getDriverId()) {
			return true;
		}
		
		return false;
	}
	
}
