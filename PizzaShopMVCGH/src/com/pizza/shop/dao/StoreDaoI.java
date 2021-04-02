package com.pizza.shop.dao;

import java.util.List;

import com.pizza.shop.entity.Store;

public interface StoreDaoI {
	public boolean addStore(Store store);
	public Store getStore(int sId);
	public boolean updateStore(Store store);
	public boolean removeStore(int sId);
	public List<Store> getAllStore();
}
