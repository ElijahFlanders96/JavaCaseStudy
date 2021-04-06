package com.pizza.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizza.shop.entity.Employee;
import com.pizza.shop.dao.EmployeeDao;

@Service
public class EmployeeService {
	
	private EmployeeDao empD = new EmployeeDao();
	
	@Autowired
	public EmployeeService(EmployeeDao empD) {
		this.empD = empD;
	}
	
	public void addEmpService(Employee emp) {
		empD.addEmp(emp);
	}
	public Employee getEmpService(int eId) {
		return empD.getEmp(eId);
	}
	public void updateEmpService(Employee emp) {
		empD.updateEmp(emp);
	}
	public List<Employee> getAllEmpService() {
		return empD.getAllEmp();
	}
	public void removeEmpService(int eId) {
		empD.removeEmp(eId);
	}
	public boolean validateEmpService(int eId, String firstName, String lastName, String email, String password, String phoneNumber, int storeId) {
		Employee empFound = empD.getEmp(eId);
		if (empFound!=null) {
			if(empFound.getFirstName().equals(firstName) && empFound.getLastName().equals(lastName) && empFound.getEmail().equals(email) && empFound.getPassword().equals(password) && empFound.getPhoneNumber().equals(phoneNumber) && empFound.getStoreId() == storeId) {
				return true;
			}
		}
		return false;
	}
}
