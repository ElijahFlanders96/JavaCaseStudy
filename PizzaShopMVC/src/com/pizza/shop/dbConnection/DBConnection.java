package com.pizza.shop.dbConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DBConnection {
	protected EntityManagerFactory emf = null;
	protected EntityManager em = null;
	// I probably need to change this
	private String pUName = "PizzaShop";
	
	public void connect() {
		this.emf = Persistence.createEntityManagerFactory(pUName);
		this.em = emf.createEntityManager();
	}
	
	public void disconnect() {
		if (this.em!=null) {
			em.close();
		}
		if (this.emf!=null) {
			emf.close();
		}
	}
}
