package com.pizza.shop.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pizza.shop.dbConnection.DBConnection;
import com.pizza.shop.entity.Machinery;

@Repository
public class MachineryDao extends DBConnection implements MachineryDaoI {

	@Override
	public boolean addMac(Machinery mac) {
		try {
			this.connect();
			em.getTransaction().begin();
			em.persist(mac);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Machinery getMac(int mId) {
		this.connect();
		Machinery macFound = em.find(Machinery.class, mId);
		this.disconnect();
		return macFound;
	}

	@Override
	public boolean updateMac(Machinery mac) {
		try {
			this.connect();
			em.getTransaction().begin();
			Machinery macFound = em.find(Machinery.class, mac.getmId());
			macFound.setName(mac.getName());
			macFound.setStatus(mac.getStatus());
			macFound.setReplacementCost(mac.getReplacementCost());
			macFound.setStoreId(mac.getStoreId());
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeMac(int mId) {
		try {
			this.connect();
			em.getTransaction().begin();
			Machinery macFound = em.find(Machinery.class, mId);
			em.remove(macFound);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Machinery> getAllMac() {
		this.connect();
		List<Machinery> macs = em.createQuery("select m from Machinery m").getResultList();
		this.disconnect();
		return macs;
	}

}
