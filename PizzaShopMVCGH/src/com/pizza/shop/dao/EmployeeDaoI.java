package com.pizza.shop.dao;

import java.util.List;

import com.pizza.shop.entity.Employee;

public interface EmployeeDaoI {
	public boolean addEmp(Employee emp);
	public Employee getEmp(int eId);
	public boolean updateEmp(Employee emp);
	public boolean removeEmp(int eId);
	public List<Employee> getAllEmp();
}
