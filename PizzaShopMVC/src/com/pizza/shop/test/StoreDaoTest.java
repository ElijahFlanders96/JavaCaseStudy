package com.pizza.shop.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import com.pizza.shop.dao.StoreDao;
import com.pizza.shop.entity.Store;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class StoreDaoTest {
	static StoreDao sDao;
	
	int storeId;
	Store storeExpected;
	
	public StoreDaoTest(int storeId, Store storeExpected) {
		this.storeId = storeId;
		this.storeExpected = storeExpected;
	}
	
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[][] {
			{1, new Store(1, "Cleveland and Grand", "41 Cleveland Ave, St Paul, MN 55105", 36)},
			{2, new Store(2, "East Side", "310 White Bear Ave, St Paul, MN 55106", 355)},
			{3, new Store(3, "Plymouth", "3015 Harbor Ln, Plymouth, MN 55447", 398)},
			{4, new Store(4, "Woodbury", "1905 Donegal Dr, Woodbury, MN 55125", 515)}
		});
	}

	@BeforeClass
	public static void setUp() throws Exception {
		sDao = new StoreDao();
	}

	@Test
	public void testAddStore() {
		System.out.println("testAddStore");
		boolean actual = sDao.addStore(this.storeExpected);
		assertTrue(actual);
	}
	
	@Test
	public void testBGetStore() {
		System.out.println("testGetStore");
		Store actual = sDao.getStore(this.storeId);
		assertEquals(this.storeExpected, actual);
	}
	
	@Test
	public void testCUpdateStore() {
		System.out.println("testUpdateStore");
		assertTrue(sDao.updateStore(this.storeExpected));
	}
	
	@Test
	public void testDRemoveStore() {
		System.out.println("testRemoveEmp");
		boolean actual = sDao.removeStore(this.storeId);
		assertEquals(true, actual);
	}

}
