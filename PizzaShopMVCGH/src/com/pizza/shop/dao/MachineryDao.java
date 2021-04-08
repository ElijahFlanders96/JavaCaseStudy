package com.pizza.shop.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pizza.shop.dbConnection.DBConnection;
import com.pizza.shop.entity.Machinery;

//The MachineryDao class defines all of the methods in the McahineryDaoI
//Here, I write the code that communicates with my database to perform CRUD functionality
@Repository
public class MachineryDao extends DBConnection implements MachineryDaoI {

	// Adds machienry to the database (CREATE)
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

	// Retrieves machinery from the database (READ)
	@Override
	public Machinery getMac(int mId) {
		this.connect();
		Machinery macFound = em.find(Machinery.class, mId);
		this.disconnect();
		return macFound;
	}

	// Updates machinery in the database (UPDATE)
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

	// Removes machinery from the database (DELETE)
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

	// Retrieves a liast of all machinery in the database
	@Override
	public List<Machinery> getAllMac() {
		this.connect();
		List<Machinery> macs = em.createQuery("select m from Machinery m").getResultList();
		this.disconnect();
		return macs;
	}

}
