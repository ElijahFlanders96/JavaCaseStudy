package com.pizza.shop.Elijah.Flanders.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import com.pizza.shop.Elijah.Flanders.dao.StoreDao;
import com.pizza.shop.Elijah.Flanders.entity.Employee;
import com.pizza.shop.Elijah.Flanders.entity.Store;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class StoreDaoTest {
	static StoreDao sDao;
	
	int storeId;
	Store storeExpected;
	
//	Employee e1 = new Employee(36, "Rocco", "Preese", 28.85, "General Manager", "roccop00@yahoo.com", "651-221-8403", 1);
//	Employee e2 = new Employee(355, "Molly", "Stevens", 25.00, "General Manager", "mstevens77@gmail.com", "651-828-3313", 2);
//	Employee e3 = new Employee(398, "Erik", "Hoffman", 23.77, "General Manager", "bigdaddyhoof98@gmail.com", "651-195-1272", 3);
//	Employee e4 = new Employee(515, "Holly", "Greene", 20.22, "General Manager", "hollygreene08@gmail.com", "651-196-5500", 4);
//	Employee e5 = new Employee(701, "Reuben", "West", 9.15, "Driver", "rsandwich11@gmail.com", "651-314-6419", 4);
//	Employee e6 = new Employee(60, "Larry", "Wirtjes", 10.50, "Driver", "wirtjesworks69@gmail.com", "651-783-9095", 1);
//	Employee e7 = new Employee(822, "Mike", "Hart", 18.00, "Shift Leader", "brokenhartz07@gmail.com", "763-438-2001", 3);
//	Employee e8 = new Employee(888, "Elijah", "Flanders", 14.75, "Certified Trainer", "elijahflanders16@gmail.com", "763-952-5091", 1);
//	
//	List<Employee> list1 = new ArrayList<Employee>();
//	list1.add(e1);
//	List<Employee> list2 = new ArrayList<Employee>();
//	List<Employee> list3 = new ArrayList<Employee>();
//	List<Employee> list4 = new ArrayList<Employee>();
	
//	static List<Employee> list = storeExpected.getEmpList();
	
	public StoreDaoTest(int storeId, Store storeExpected) {
		this.storeId = storeId;
		this.storeExpected = storeExpected;
	}
	
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[][] {
			{1, new Store(1, "Cleveland and Grand", "41 Cleveland Ave, St Paul, MN 55105", 36, null, null)},
			{2, new Store(2, "East Side", "310 White Bear Ave, St Paul, MN 55106", 355, null, null)},
			{3, new Store(3, "Plymouth", "3015 Harbor Ln, Plymouth, MN 55447", 398, null, null)},
			{4, new Store(4, "Woodbury", "1905 Donegal Dr, Woodbury, MN 55125", 515, null, null)}
		});
	}

	@BeforeClass
	public static void setUp() throws Exception {
		sDao = new StoreDao();
	}

	@Test
	public void testBAddStore() {
		System.out.println("testAddStore");
		boolean actual = sDao.addStore(this.storeExpected);
		assertTrue(actual);
	}
	
	@Test
	public void testCGetStore() {
		System.out.println("testGetStore");
		Store actual = sDao.getStore(this.storeId);
		assertEquals(this.storeExpected, actual);
	}
	
	@Test
	public void testDUpdateStore() {
		System.out.println("testUpdateStore");
		assertTrue(sDao.updateStore(this.storeExpected));
	}
	
	@Test
	public void testARemoveStore() {
		System.out.println("testRemoveEmp");
		boolean actual = sDao.removeStore(this.storeId);
		assertEquals(true, actual);
	}

}
