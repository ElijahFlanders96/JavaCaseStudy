package com.pizza.shop.Elijah.Flanders.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Code for the Entity class / table in my database complete with constructors, getters, and setters
@Entity(name="Machinery")
@Table(name="Machinery")
public class Machinery {
	@Id
	@Column
	private int mId;
	@Column
	private String name;
	@Column
	private int status;
	// status will be an option between:
	// 1: "working", 2: "requires maintenance", 3: "requires replacement"
	@Column
	private double replacementCost;
	@Column
	private int storeId;
	
	public Machinery() {
		super();
	}

	public Machinery(int mId, String name, int status, double replacementCost, int storeId) {
		super();
		this.mId = mId;
		this.name = name;
		this.status = status;
		this.replacementCost = replacementCost;
		this.storeId = storeId;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	// Overriding the toString() method to format it how I want it to be formatted
	@Override
	public String toString() {
		return String.format("\nID: %-20s, Name: %-20s, Status: %-20s, Replacement Cost: %-20s, Store ID: %-20s\n", mId, name, status, replacementCost, storeId);
	}
	
	// Overriding the equals() method so that the test cases evaluate the values of the objects instead of their position in the heap
	@Override
	public boolean equals(Object o) {
		Machinery comparedTo = (Machinery) o;
		if (this.mId==comparedTo.getmId()
			&& this.name.equals(comparedTo.getName())
			&& this.status==comparedTo.getStatus()
			&& this.replacementCost==comparedTo.getReplacementCost() 
			&& this.storeId==comparedTo.getStoreId()) {
			return true;
		}
		
		return false;
	}
}
