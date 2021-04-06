package com.pizza.shop.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	DriverVehicleDaoTest.class, 
	EmployeeDaoTest.class, 
	MachineryDaoTest.class, 
	StoreDaoTest.class })
public class AllTests {

}
