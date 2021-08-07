package com.app.service;

import java.util.List;

import com.app.exceptions.customExceptions.DatabaseException;
import com.app.model.Employee;

public interface ServiceInterface {

	 Employee addEmployee(Employee emp) throws DatabaseException;
	
	 Employee updateEmployee(int id, Employee employee) throws DatabaseException;
	
	 boolean deleteEmployee(int id) throws DatabaseException;
	
	 List<Employee> getAllEmp() throws DatabaseException;

	 Employee getEmp(int id) throws DatabaseException; 
}
