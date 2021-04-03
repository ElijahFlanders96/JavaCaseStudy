package com.pizza.shop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	@OneToMany(targetEntity=Employee.class)
	private List<Employee> empList;
	@OneToMany(targetEntity=Machinery.class)
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

	@Override
	public String toString() {
		// need to fill out the first argument, need to figure out how and don't want to rn
		return String.format("", sId, name, address, gmId);
	}
	
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
