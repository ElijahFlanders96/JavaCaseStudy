package com.pizza.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Employee")
@Table(name="Employee")
public class Employee {
	@Id
	@Column
	private int eId;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private double wage;
	@Column
	private String position;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String phoneNumber;
	@Column
	private int storeId;
	
	public Employee() {
		super();
	}

	public Employee(int eId, String firstName, String lastName, double wage, String position, String email, String password, String phoneNumber, int storeId) {
		super();
		this.eId = eId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.wage = wage;
		this.position = position;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.storeId = storeId;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getWage() {
		return wage;
	}

	public void setWage(double wage) {
		this.wage = wage;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	@Override
	public String toString() {
		// need to fill out the first argument, need to figure out how and don't want to rn
		return String.format("\nID: %-20s Name: %-20s %-20s Wage: %-20s Position: %-20s Email: %-20s Phone Number: %-20s Store ID: %-20s\n", eId, firstName, lastName, wage, position, email, phoneNumber, storeId);
	}
	
	@Override
	public boolean equals(Object o) {
		Employee comparedTo = (Employee) o;
		if (this.eId==comparedTo.geteId()
			&& this.firstName.equals(comparedTo.getFirstName())
			&& this.lastName.equals(comparedTo.getLastName())
			&& this.wage==comparedTo.getWage()
			&& this.position.equals(comparedTo.getPosition()) 
			&& this.email.equals(comparedTo.getEmail())
			&& this.password.equals(comparedTo.getPassword())
			&& this.phoneNumber.equals(comparedTo.getPhoneNumber()) 
			&& this.storeId==comparedTo.getStoreId()) {
			return true;
		}
		
		return false;
	}
	
}
