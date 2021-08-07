package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.app.dao.EmployeeDAO;
import com.app.exceptions.customExceptions.DatabaseException;
import com.app.exceptions.customExceptions.RecordNotFoundException;
import com.app.model.Employee;

@Service
public class ServiceImpl implements ServiceInterface {

	@Autowired
	private EmployeeDAO dao;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Employee addEmployee(Employee emp) throws DatabaseException {

		try {
			Employee employeeObj = dao.save(emp);
			return employeeObj;

		} catch (DataAccessException e) {
			throw new DatabaseException(e);
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	@CachePut(cacheNames="employee",key="#id")
	public Employee updateEmployee(int id, Employee employeeIn) throws DatabaseException {

		try {
			if (!dao.existsById(id)) {
				throw new RecordNotFoundException("No student Record found");
			}
			Employee employeeObj = dao.save(employeeIn);
			
			return employeeObj;
		} catch (DataAccessException e) {
			throw new DatabaseException(e);
		}

	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	@CacheEvict(cacheNames="employees",key="#id")
	public boolean deleteEmployee(int id) throws DatabaseException {
		
		try {
			
			if (!dao.existsById(id)) {
				throw new RecordNotFoundException("No student Record found");
			}
			else {
			dao.deleteById(id);
			return true;
			
			}
			
		} catch (DataAccessException e) {
			throw new DatabaseException(e);
		}
		
	
	}

	@Transactional(propagation=Propagation.NEVER)
	@Override
	@Cacheable(cacheNames="employees",key="#employees.id")
	public List<Employee> getAllEmp() throws DatabaseException {
		try {
			List<Employee> employeeList = (List<Employee>) dao.findAll();// TODO Auto-generated method stub
			return employeeList;
		} catch (DataAccessException e) {
			throw new DatabaseException(e);
		}
	}
	
	@Transactional(propagation=Propagation.NEVER)
	@Override
	@Cacheable(cacheNames="employees",key="#id")
	public Employee getEmp(int id) throws DatabaseException {
		try {
			Employee employee =dao.findById(id).get();// TODO Auto-generated method stub
			return employee;
		} catch (DataAccessException e) {
			throw new DatabaseException(e);
		}
	}
	

}
