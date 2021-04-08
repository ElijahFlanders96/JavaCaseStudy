package com.pizza.shop.Elijah.Flanders.dao;

import java.util.List;

import com.pizza.shop.Elijah.Flanders.entity.Employee;
import com.pizza.shop.Elijah.Flanders.entity.Machinery;
import com.pizza.shop.Elijah.Flanders.entity.Store;

//Interface for the StoreDao - asserts what methods will need to be defined
public interface StoreDaoI {
	public boolean addStore(Store store);
	public Store getStore(int sId);
	public boolean updateStore(Store store);
	public boolean removeStore(int sId);
	public List<Store> getAllStore();
	public void addEmpToStore(int eId, int sId);
	public void addMacToStore(int mId, int sId);
	public void removeEmpFromStore(int eId, int sId);
	public void removeMacFromStore(int mId, int sId);
	public List<Employee> viewAllEmp(int sId);
	public List<Machinery> viewAllMac(int sId);
}
