package com.pizza.shop.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.pizza.shop.entity.DriverVehicle;
import com.pizza.shop.entity.Employee;
import com.pizza.shop.entity.Machinery;
import com.pizza.shop.entity.Store;

//import java.util.Scanner;

public class PizzaShopAppMain {
	
//	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PizzaShop");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
//		Store s1 = new Store(1, "Cleveland and Grand", "41 Cleveland Ave, St Paul, MN 55105", 36);
//		Store s2 = new Store(2, "East Side", "310 White Bear Ave, St Paul, MN 55106", 355);
//		Store s3 = new Store(3, "Plymouth", "3015 Harbor Ln, Plymouth, MN 55447", 398);
//		Store s4 = new Store(4, "Woodbury", "1905 Donegal Dr, Woodbury, MN 55125", 515);
//		
//		em.persist(s1);
//		em.persist(s2);
//		em.persist(s3);
//		em.persist(s4);
		
//		Employee e1 = new Employee(36, "Rocco", "Preese", 28.85, "General Manager", "roccop00@yahoo.com", "651-221-8403", 1);
//		Employee e2 = new Employee(355, "Molly", "Stevens", 25.00, "General Manager", "mstevens77@gmail.com", "651-828-3313", 2);
//		Employee e3 = new Employee(398, "Erik", "Hoffman", 23.77, "General Manager", "bigdaddyhoof98@gmail.com", "651-195-1272", 3);
//		Employee e4 = new Employee(515, "Holly", "Greene", 20.22, "General Manager", "hollygreene08@gmail.com", "651-196-5500", 4);
//		Employee e5 = new Employee(701, "Reuben", "West", 9.15, "Driver", "rsandwich11@gmail.com", "651-314-6419", 4);
//		Employee e6 = new Employee(60, "Larry", "Wirtjes", 10.50, "Driver", "wirtjesworks69@gmail.com", "651-783-9095", 1);
//		Employee e7 = new Employee(822, "Mike", "Hart", 18.00, "Shift Leader", "brokenhartz07@gmail.com", "763-438-2001", 3);
//		Employee e8 = new Employee(888, "Elijah", "Flanders", 14.75, "Certified Trainer", "elijahflanders16@gmail.com", "763-952-5091", 1);
//		
//		em.persist(e1);
//		em.persist(e2);
//		em.persist(e3);
//		em.persist(e4);
//		em.persist(e5);
//		em.persist(e6);
//		em.persist(e7);
//		em.persist(e8);
		
		DriverVehicle d1 = new DriverVehicle(71, "Chevy Impala", 2008, "blue", "State Farm", 701);
		DriverVehicle d2 = new DriverVehicle(4, "Jeep Wrangler", 2000, "black", "Progressive", 60);
		
		em.persist(d1);
		em.persist(d2);
//		
//		Machinery m1 = new Machinery(21, "Oven 1", 1, 50000, 1);
//		Machinery m2 = new Machinery(144, "H-oven", 2, 15000, 1);
//		Machinery m3 = new Machinery(39, "Pie Bar", 1, 33000, 2);
//		Machinery m4 = new Machinery(40, "Hoagie Bar", 1, 32000, 2);
//		Machinery m5 = new Machinery(85, "Dough Mixer", 2, 18000, 3);
//		Machinery m6 = new Machinery(157, "Register 1", 1, 3000, 3);
//		Machinery m7 = new Machinery(204, "Office Computer", 1, 15000, 4);
//		Machinery m8 = new Machinery(100, "Freezer", 1, 25000, 4);
//		
//		em.persist(m1);
//		em.persist(m2);
//		em.persist(m3);
//		em.persist(m4);
//		em.persist(m5);
//		em.persist(m6);
//		em.persist(m7);
//		em.persist(m8);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		System.out.println("Added to db successfully");
	}

}
