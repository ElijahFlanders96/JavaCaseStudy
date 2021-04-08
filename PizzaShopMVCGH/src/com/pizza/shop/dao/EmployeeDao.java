package com.pizza.shop.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.pizza.shop.dbConnection.DBConnection;
import com.pizza.shop.entity.Employee;

// The EmployeeDao class defines all of the methods in the EmployeeDaoI
// Here, I write the code that communicates with my database to perform CRUD functionality
@Repository
public class EmployeeDao extends DBConnection implements EmployeeDaoI {

	// Adds an employee to the database (CREATE)
	@Override
	public boolean addEmp(Employee emp) {
		try {
			this.connect();
			em.getTransaction().begin();
			em.persist(emp);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Retrieves and employee from the database (READ)
	@Override
	public Employee getEmp(int eId) {
		this.connect();
		Employee empFound = em.find(Employee.class, eId);
		this.disconnect();
		return empFound;
	}

	// Updates an employee in the database (UPDATE)
	@Override
	public boolean updateEmp(Employee emp) {
		try {
			this.connect();
			em.getTransaction().begin();
			Employee empFound = em.find(Employee.class, emp.geteId());
			empFound.setFirstName(emp.getFirstName());
			empFound.setLastName(emp.getLastName());
			empFound.setWage(emp.getWage());
			empFound.setPosition(emp.getPosition());
			empFound.setEmail(emp.getEmail());
			empFound.setPassword(emp.getPassword());
			empFound.setPhoneNumber(emp.getPhoneNumber());
			empFound.setStoreId(emp.getStoreId());
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Removes an employee from the database (DELETE)
	@Override
	public boolean removeEmp(int eId) {
		try {
			this.connect();
			em.getTransaction().begin();
			Employee empFound = em.find(Employee.class, eId);
			em.remove(empFound);
			em.getTransaction().commit();
			this.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Retrieves a list of all employees in the database
	@Override
	public List<Employee> getAllEmp() {
		this.connect();
		List<Employee> emps = em.createQuery("select e from Employee e").getResultList();
		this.disconnect();
		return emps;
	}

}
