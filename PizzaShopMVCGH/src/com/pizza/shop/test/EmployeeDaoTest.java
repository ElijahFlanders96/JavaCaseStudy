package com.pizza.shop.test;

import static org.junit.Assert.*;

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

import com.pizza.shop.dao.EmployeeDao;
import com.pizza.shop.entity.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class EmployeeDaoTest {
	static EmployeeDao eDao;
	
	int empId;
	Employee empExpected;
	
	public EmployeeDaoTest(int empId, Employee empExpected) {
		this.empId = empId;
		this.empExpected = empExpected;
	}
	
	@Parameterized.Parameters
	public static Collection params() {
		return Arrays.asList(new Object[][] {
			{36, new Employee(36, "Rocco", "Preese", 28.85, "General Manager", "roccop00@yahoo.com", "p1hF5kA21", "651-221-8403", 1)},
			{355, new Employee(355, "Molly", "Stevens", 25.00, "General Manager", "mstevens77@gmail.com", "B84d8MM4l", "651-828-3313", 2)},
			{398, new Employee(398, "Erik", "Hoffman", 23.77, "General Manager", "bigdaddyhoof98@gmail.com", "5TyU110gJ", "651-195-1272", 3)},
			{515, new Employee(515, "Holly", "Greene", 20.22, "General Manager", "hollygreene08@gmail.com", "0A0fRVk6M", "651-196-5500", 4)},
			{701, new Employee(701, "Reuben", "West", 9.15, "Driver", "rsandwich11@gmail.com", "Yc7C30f4A", "651-314-6419", 4)},
			{60, new Employee(60, "Larry", "Wirtjes", 10.50, "Driver", "wirtjesworks69@gmail.com", "is95XNDe1", "651-783-9095", 1)},
			{822, new Employee(822, "Mike", "Hart", 18.00, "Shift Leader", "brokenhartz07@gmail.com", "95T12bNxK", "763-438-2001", 3)},
			{888, new Employee(888, "Elijah", "Flanders", 14.75, "Certified Trainer", "elijahflanders16@gmail.com", "fRe8z1oWM", "763-952-5091", 1)}
		});
	}

	@BeforeClass
	public static void setUp() throws Exception {
		eDao = new EmployeeDao();
	}

	@Test
	public void testAddEmp() {
		System.out.println("testAddEmp");
		boolean actual = eDao.addEmp(this.empExpected);
		assertTrue(actual);
	}
	
	@Test
	public void testBGetEmp() {
		System.out.println("testGetEmp");
		Employee actual = eDao.getEmp(this.empId);
		assertEquals(this.empExpected, actual);
	}
	
	@Test
	public void testCUpdateEmp() {
		System.out.println("testUpdateEmp");
		assertTrue(eDao.updateEmp(this.empExpected));
	}
	
	@Test
	public void testDRemoveEmp() {
		System.out.println("testRemoveEmp");
		boolean actual = eDao.removeEmp(this.empId);
		assertEquals(true, actual);
	}

}