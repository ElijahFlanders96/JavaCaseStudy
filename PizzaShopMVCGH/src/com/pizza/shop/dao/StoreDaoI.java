package com.pizza.shop.dao;

import java.util.List;

import com.pizza.shop.entity.Employee;
import com.pizza.shop.entity.Machinery;
import com.pizza.shop.entity.Store;

public interface StoreDaoI {
	public boolean addStore(Store store);
	public Store getStore(int sId);
	public boolean updateStore(Store store);
	public boolean removeStore(int sId);
	public List<Store> getAllStore();
//	public void addEmpToStore(int eId, int sId);
//	public void addMacToStore(int mId, int sId);
	public List<Employee> viewAllEmp(int sId);
	public List<Machinery> viewAllMac(int sId);
}
