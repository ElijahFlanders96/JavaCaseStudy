package com.pizza.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	public Store() {
		super();
	}

	public Store(int sId, String name, String address, int gmId) {
		super();
		this.sId = sId;
		this.name = name;
		this.address = address;
		this.gmId = gmId;
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
