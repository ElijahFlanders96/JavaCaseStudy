package com.pizza.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizza.shop.dao.StoreDao;
import com.pizza.shop.entity.Employee;
import com.pizza.shop.entity.Machinery;
import com.pizza.shop.entity.Store;

@Service
public class StoreService {
	
	private StoreDao storeD = new StoreDao();
	
	@Autowired
	public StoreService(StoreDao storeD) {
		this.storeD = storeD;
	}
	
	public void addStoreService(Store store) {
		storeD.addStore(store);
	}
	public Store getStoreService(int sId) {
		return storeD.getStore(sId);
	}
	public List<Store> getAllStoreService() {
		return storeD.getAllStore();
	}
	public void updateStoreService(Store store) {
		storeD.updateStore(store);
	}
	public void removeStoreService(int sId) {
		storeD.removeStore(sId);
	}
//	public void addEmpToStoreService(int eId, int sId) {
//		storeD.addEmpToStore(eId, sId);
//	}
//	public void addMacToStoreService(int mId, int sId) {
//		storeD.addMacToStore(mId, sId);
//	}
	public List<Employee> viewAllEmpService(int sId) {
		return storeD.viewAllEmp(sId);
	}
	public List<Machinery> viewAllMacService(int sId) {
		return storeD.viewAllMac(sId);
	}
	public boolean validateStoreService(int sId, String name, String address) {
		Store storeFound = storeD.getStore(sId);
		if (storeFound!=null) {
			if (storeFound.getName().equals(name) && storeFound.getAddress().equals(address)) {
				return true;
			}
		}
		return false;
	}
}
