package com.pizza.shop.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.pizza.shop.dao.DriverVehicleDao;
import com.pizza.shop.entity.DriverVehicle;

@RunWith(Parameterized.class)
public class DriverVehicleDaoTest {
	static DriverVehicleDao dDao;
	
	int carId;
	DriverVehicle carExpected;
	
	public DriverVehicleDaoTest(int carId, DriverVehicle carExpected) {
		this.carId = carId;
		this.carExpected = carExpected;
	}
	
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[][] {
			{71, new DriverVehicle(71, "Chevy Impala", 2008, "blue", "State Farm", 701)},
			{4, new DriverVehicle(4, "Jeep Wrangler", 2000, "black", "Progressive", 60)}
		});
	}

	@BeforeClass
	public static void setUp() throws Exception {
		dDao = new DriverVehicleDao();
	}

	@Test
	public void testAddCar() {
		System.out.println("testAddCar");
		boolean actual = dDao.addCar(this.carExpected);
		assertTrue(actual);
	}
	
	@Test
	public void testBGetCar() {
		System.out.println("testGetCar");
		DriverVehicle actual = dDao.getCar(this.carId);
		assertEquals(this.carExpected, actual);
	}
	
	@Test
	public void testCUpdateCar() {
		System.out.println("testUpdateCar");
		assertTrue(dDao.updateCar(this.carExpected));
	}
	
	@Test
	public void testDRemoveCar() {
		System.out.println("testRemoveCar");
		boolean actual = dDao.removeCar(this.carId);
		assertEquals(true, actual);
	}

}
