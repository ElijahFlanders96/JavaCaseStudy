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

import com.pizza.shop.dao.MachineryDao;
import com.pizza.shop.entity.Machinery;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class MachineryDaoTest {
	static MachineryDao mDao;
	
	int macId;
	Machinery macExpected;
	
	public MachineryDaoTest(int macId, Machinery macExpected) {
		this.macId = macId;
		this.macExpected = macExpected;
	}
	
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[][] {
			{21, new Machinery(21, "Oven 1", 1, 50000, 1)},
			{144, new Machinery(144, "H-oven", 2, 15000, 1)},
			{39, new Machinery(39, "Pie Bar", 1, 33000, 2)},
			{40, new Machinery(40, "Hoagie Bar", 1, 32000, 2)},
			{85, new Machinery(85, "Dough Mixer", 2, 18000, 3)},
			{157, new Machinery(157, "Register 1", 1, 3000, 3)},
			{204, new Machinery(204, "Office Computer", 1, 15000, 4)},
			{100, new Machinery(100, "Freezer", 1, 25000, 4)}
		});
	}

	@BeforeClass
	public static void setUp() throws Exception {
		mDao = new MachineryDao();
	}

	@Test
	public void testBAddMac() {
		System.out.println("testAddMac");
		boolean actual = mDao.addMac(this.macExpected);
		assertTrue(actual);
	}
	
	@Test
	public void testCGetMac() {
		System.out.println("testGetMac");
		Machinery actual = mDao.getMac(this.macId);
		assertEquals(this.macExpected, actual);
	}
	
	@Test
	public void testDUpdateMac() {
		System.out.println("testUpdateMac");
		assertTrue(mDao.updateMac(this.macExpected));
	}
	
	@Test
	public void testARemoveMac() {
		System.out.println("testRemoveMac");
		boolean actual = mDao.removeMac(this.macId);
		assertEquals(true, actual);
	}

}
