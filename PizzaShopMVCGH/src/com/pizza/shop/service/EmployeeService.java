package com.pizza.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizza.shop.entity.Employee;
import com.pizza.shop.dao.EmployeeDao;

// This is the service from the EmployeeDao
// These methods are called in the HomeController instead of the dao methods themselves
@Service
public class EmployeeService {
	
	private EmployeeDao empD = new EmployeeDao();
	
	@Autowired
	public EmployeeService(EmployeeDao empD) {
		this.empD = empD;
	}
	
	// Calls the Add Employee method from the dao
	public void addEmpService(Employee emp) {
		empD.addEmp(emp);
	}
	// Calls the Get Employee method from the dao
	public Employee getEmpService(int eId) {
		return empD.getEmp(eId);
	}
	// Calls the Update Employee method from the dao
	public void updateEmpService(Employee emp) {
		empD.updateEmp(emp);
	}
	// Calls the Get All Employee method from the dao
	public List<Employee> getAllEmpService() {
		return empD.getAllEmp();
	}
	// Calls the Remove Employee method from the dao
	public void removeEmpService(int eId) {
		empD.removeEmp(eId);
	}
	
}
