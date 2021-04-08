package com.pizza.shop.Elijah.Flanders.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pizza.shop.Elijah.Flanders.dbConnection.DBConnection;
import com.pizza.shop.Elijah.Flanders.entity.Employee;
import com.pizza.shop.Elijah.Flanders.entity.Machinery;
import com.pizza.shop.Elijah.Flanders.entity.Store;

//The StoreDao class defines all of the methods in the StoreDaoI
//Here, I write the code that communicates with my database to perform CRUD functionality
@Repository
public class StoreDao extends DBConnection implements StoreDaoI {

	// Adds a store to the database (CREATE)
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

	// Retrieves a store from the database (READ)
	@Override
	public Store getStore(int sId) {
		this.connect();
		Store storeFound = em.find(Store.class, sId);
		this.disconnect();
		return storeFound;
	}

	// Updates a store in the database (UPDATE)
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

	// Removes a store from the database (DELETE)
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

	// Retrieves a list of all stores in the database
	@Override
	public List<Store> getAllStore() {
		this.connect();
		List<Store> stores = em.createQuery("select s from Store s").getResultList();
		this.disconnect();
		return stores;
	}
	
	// These next methods deviate from standard CRUD functionality
	// These methods define how the relationship between Store and Employee and Machinery operate via the database
	
	// Adds an employee to a store's empList
	// Called when an employee is added to the database, or if an employee is updated and has a modified store ID
	@Override
	public void addEmpToStore(int eId, int sId) {
		this.connect();
		em.getTransaction().begin();
		Employee empFound = em.find(Employee.class, eId);
		Store storeFound = em.find(Store.class, sId);
		List<Employee> emps = storeFound.getEmpList();
		emps.add(empFound);
		em.getTransaction().commit();
		this.disconnect();
	}

	// Adds machinery to a store's macList
	// Called when machinery is added to the database, or if machinery is updated and has a modified store ID
	@Override
	public void addMacToStore(int mId, int sId) {
		this.connect();
		em.getTransaction().begin();
		Machinery macFound = em.find(Machinery.class, mId);
		Store storeFound = em.find(Store.class, sId);
		List<Machinery> macs = storeFound.getMacList();
		macs.add(macFound);
		em.getTransaction().commit();
		this.disconnect();
	}
	
	// Removes an employee from a store's empList
	// Called when an employee is removed from the database, or when an employee is updated with a modified store ID
	@Override
	public void removeEmpFromStore(int eId, int sId) {
		this.connect();
		em.getTransaction().begin();
		Employee empFound = em.find(Employee.class, eId);
		Store storeFound =em.find(Store.class, sId);
		List<Employee> emps = storeFound.getEmpList();
		emps.remove(empFound);
		em.getTransaction().commit();
		this.disconnect();
	}

	// Removes machinery from a store's macList
	// Called when machinery is removed from the database, or when machinery is updated with a modified store ID
	@Override
	public void removeMacFromStore(int mId, int sId) {
		this.connect();
		em.getTransaction().begin();
		Machinery macFound = em.find(Machinery.class, mId);
		Store storeFound =em.find(Store.class, sId);
		List<Machinery> macs = storeFound.getMacList();
		macs.remove(macFound);
		em.getTransaction().commit();
		this.disconnect();
	}

	// Retrieves a list of all employees that share a store ID
	@Override
	public List<Employee> viewAllEmp(int sId) {
		return this.getStore(sId).getEmpList();
	}

	// Retrieves a list of all machinery that shares a store ID
	@Override
	public List<Machinery> viewAllMac(int sId) {
		return this.getStore(sId).getMacList();
	}
	
}
