package com.pizza.shop.Elijah.Flanders.dao;

import java.util.List;

import com.pizza.shop.Elijah.Flanders.entity.Machinery;

//Interface for the MachineryDao - asserts what methods will need to be defined
public interface MachineryDaoI {
	public boolean addMac(Machinery mac);
	public Machinery getMac(int mId);
	public boolean updateMac(Machinery mac);
	public boolean removeMac(int mId);
	public List<Machinery> getAllMac();
}
