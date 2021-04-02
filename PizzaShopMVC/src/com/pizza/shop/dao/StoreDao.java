package com.pizza.shop.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pizza.shop.dbConnection.DBConnection;
import com.pizza.shop.entity.Employee;
import com.pizza.shop.entity.Store;

@Repository
public class StoreDao extends DBConnection implements StoreDaoI {

	@Override
	public boolean addStore(Store store) {
		try {
			this.connect();
			em.getTransaction().begin();
			em.persist(store);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Store getStore(int sId) {
		this.connect();
		Store storeFound = em.find(Store.class, sId);
		this.disconnect();
		return storeFound;
	}

	@Override
	public boolean updateStore(Store store) {
		try {
			this.connect();
			em.getTransaction().begin();
			Store storeFound = em.find(Store.class, store.getsId());
			storeFound.setName(store.getName());
			storeFound.setAddress(store.getAddress());
			storeFound.setGmId(store.getGmId());
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeStore(int sId) {
		try {
			this.connect();
			em.getTransaction().begin();
			Store storeFound = em.find(Store.class, sId);
			em.remove(storeFound);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Store> getAllStore() {
		this.connect();
		List<Store> stores = em.createQuery("select s from Store s").getResultList();
		this.disconnect();
		return stores;
	}

}
