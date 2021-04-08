package com.pizza.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizza.shop.dao.MachineryDao;
import com.pizza.shop.entity.Machinery;

//This is the service from the MachineryDao
//These methods are called in the HomeController instead of the dao methods themselves
@Service
public class MachineryService {
	
	private MachineryDao macD = new MachineryDao();
	
	@Autowired
	public MachineryService(MachineryDao macD) {
		this.macD = macD;
	}
	
	// Calls the Add Machinery method from the dao
	public void addMacService(Machinery mac) {
		macD.addMac(mac);
	}
	// Calls the Get Machinery method from the dao
	public Machinery getMacService(int mId) {
		return macD.getMac(mId);
	}
	// Calls the Update Machinery method from the dao
	public void updateMacService(Machinery mac) {
		macD.updateMac(mac);
	}
	// Calls the Get All Machinery method from the dao
	public List<Machinery> getAllMacService() {
		return macD.getAllMac();
	}
	// Calls the Remove Machinery method from the dao
	public void removeMacService(int mId) {
		macD.removeMac(mId);
	}
}
