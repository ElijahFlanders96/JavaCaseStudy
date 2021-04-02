package com.pizza.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizza.shop.dao.MachineryDao;
import com.pizza.shop.entity.Machinery;

@Service
public class MachineryService {
	
	private MachineryDao macD = new MachineryDao();
	
	@Autowired
	public MachineryService(MachineryDao macD) {
		this.macD = macD;
	}
	
	public void addMacService(Machinery mac) {
		macD.addMac(mac);
	}
	public Machinery getMacService(int mId) {
		return macD.getMac(mId);
	}
	public void updateMacService(Machinery mac) {
		macD.updateMac(mac);
	}
	public List<Machinery> getAllMacService() {
		return macD.getAllMac();
	}
	public void removeMacService(int mId) {
		macD.removeMac(mId);
	}
	public boolean validateMacService(int mId, String name, int storeId) {
		Machinery macFound = macD.getMac(mId);
		if (macFound!=null) {
			if (macFound.getName().equals(name) && macFound.getStoreId() == storeId) {
				return true;
			}
		}
		return false;
	}
}
