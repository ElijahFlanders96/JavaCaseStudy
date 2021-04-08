package com.pizza.shop.Elijah.Flanders.dbConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// This class defines methods that connect and disconnect from the database
public abstract class DBConnection {
	protected EntityManagerFactory emf = null;
	protected EntityManager em = null;
	// I probably need to change this
	private String pUName = "PizzaShop";
	
	// Connects to the database
	public void connect() {
		this.emf = Persistence.createEntityManagerFactory(pUName);
		this.em = emf.createEntityManager();
	}
	
	// Disconnects from the database
	public void disconnect() {
		if (this.em!=null) {
			em.close();
		}
		if (this.emf!=null) {
			emf.close();
		}
	}
}
