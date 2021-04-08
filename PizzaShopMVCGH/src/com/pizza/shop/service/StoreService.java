package com.pizza.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizza.shop.dao.StoreDao;
import com.pizza.shop.entity.Employee;
import com.pizza.shop.entity.Machinery;
import com.pizza.shop.entity.Store;

//This is the service from the StoreDao
//These methods are called in the HomeController instead of the dao methods themselves
@Service
public class StoreService {
	
	private StoreDao storeD = new StoreDao();
	
	@Autowired
	public StoreService(StoreDao storeD) {
		this.storeD = storeD;
	}
	
	// Calls the Add Store method from the dao
	public void addStoreService(Store store) {
		storeD.addStore(store);
	}
	// Calls the Get Store method from teh dao
	public Store getStoreService(int sId) {
		return storeD.getStore(sId);
	}
	// Calls the Get All Stores method from the dao
	public List<Store> getAllStoreService() {
		return storeD.getAllStore();
	}
	// Calls the Update Store method from the dao
	public void updateStoreService(Store store) {
		storeD.updateStore(store);
	}
	// Calls the Remove Store method from the dao
	public void removeStoreService(int sId) {
		storeD.removeStore(sId);
	}
	// Calls the method that adds an employee to a store's empList from the dao
	public void addEmpToStoreService(int eId, int sId) {
		storeD.addEmpToStore(eId, sId);
	}
	// Calls the method that adds machinery to a store's macList from the dao
	public void addMacToStoreService(int mId, int sId) {
		storeD.addMacToStore(mId, sId);
	}
	// Calls the method that removes an employee from a store's empList from the dao
	public void removeEmpFromStoreService(int eId, int sId) {
		storeD.removeEmpFromStore(eId, sId);
	}
	// Calls the method that removes machinery from a store's macList from the dao
	public void removeMacFromStoreService(int mId, int sId) {
		storeD.removeMacFromStore(mId,sId);
	}
	// Calls the method that retrieves all employees that share a store ID from the dao
	public List<Employee> viewAllEmpService(int sId) {
		return storeD.viewAllEmp(sId);
	}
	// Calls the method that retrieves all machinery that share a store ID from the dao
	public List<Machinery> viewAllMacService(int sId) {
		return storeD.viewAllMac(sId);
	}
//	public boolean validateStoreService(int sId, String name, String address) {
//		Store storeFound = storeD.getStore(sId);
//		if (storeFound!=null) {
//			if (storeFound.getName().equals(name) && storeFound.getAddress().equals(address)) {
//				return true;
//			}
//		}
//		return false;
//	}
}
