package com.pizza.shop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// Code for the Entity class / table in my database complete with constructors, getters, and setters
@Entity(name="Store")
@Table(name="Store")
public class Store {
	@Id
	@Column
	private int sId;
	@Column
	private String name;
	@Column
	private String address;
	@Column
	private int gmId;
	// Establishing a relationship between the store table and the employee and machinery table in the database
	// Each store will have a list of employees and machinery associated with that store via the store ID
	// FetchType.EAGER allows me to grab lists from the database and display it on the front end
	@OneToMany(targetEntity=Employee.class, fetch=FetchType.EAGER)
	private List<Employee> empList;
	@OneToMany(targetEntity=Machinery.class, fetch=FetchType.EAGER)
	private List<Machinery> macList;
	
	public Store() {
		super();
	}

	public Store(int sId, String name, String address, int gmId, List<Employee> empList, List<Machinery> macList) {
		super();
		this.sId = sId;
		this.name = name;
		this.address = address;
		this.gmId = gmId;
		this.empList = empList;
		this.macList = macList;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getGmId() {
		return gmId;
	}

	public void setGmId(int gmId) {
		this.gmId = gmId;
	}
	
	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public List<Machinery> getMacList() {
		return macList;
	}

	public void setMacList(List<Machinery> macList) {
		this.macList = macList;
	}

	// Overriding the toString() method to format it how I want it to be formatted
	@Override
	public String toString() {
		return String.format("ID: %-20s, Name: %-20s, Address: %-20s, gmId: %-20s", sId, name, address, gmId);
	}
	
	// Overriding the equals() method so that the test cases evaluate the values of the objects instead of their position in the heap
	@Override
	public boolean equals(Object o) {
		Store comparedTo = (Store) o;
		if (this.sId==comparedTo.getsId()
			&& this.name.equals(comparedTo.getName())
			&& this.address.equals(comparedTo.getAddress())
			&& this.gmId==comparedTo.getGmId()) {
			return true;
		}
		
		return false;
	}
}
