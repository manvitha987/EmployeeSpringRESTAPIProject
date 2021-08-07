package com.app.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.customExceptions.DatabaseException;
import com.app.model.Employee;
import com.app.service.ServiceImpl;

@RestController
@RequestMapping(value = "/data")
public class EmployeeController {

	@Autowired
	private ServiceImpl service;

	@RequestMapping(value = "/")
	public String getResponse() {
		return "Welcome to this Rest API";
	}

	// Add new Records
	// http://localhost:9510/data/add
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { "application/json", "application/xml" })
	public ResponseEntity<Employee> addNewPerson(@Valid @RequestBody Employee employeeIn) throws DatabaseException {

		Employee employeeObj = service.addEmployee(employeeIn);

		if (employeeObj != null)
			return new ResponseEntity<Employee>(employeeObj, HttpStatus.CREATED);
		else
			return new ResponseEntity<Employee>(employeeObj, HttpStatus.BAD_REQUEST);
	}

	// GetAllRecords
	// http://localhost:9510/data/getall
	@RequestMapping(path = "/getall", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	public List<Employee> getEmployee() throws DatabaseException {

		List<Employee> employeeList = (List<Employee>) service.getAllEmp();

		return employeeList;

	}

	// Update record
	// http://localhost:9510/data/update/1
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<Employee> updateAccount(@PathVariable int id, @RequestBody Employee employeeIn)
			throws DatabaseException {

		Employee obj = service.updateEmployee(id, employeeIn);

		if (obj != null) {

			return new ResponseEntity<Employee>(obj, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);

	}

	// getby id record
	// http://localhost:9510/data/getbyid/1
	@RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<Employee> getEmp(@PathVariable int id) throws DatabaseException {

		Employee obj = service.getEmp(id);

		if (obj != null) {

			return new ResponseEntity<Employee>(obj, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);

	}

	// Delete Record by Id
	// http://localhost:9510/data/delete/1
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity removeUser(@PathVariable int id) throws DatabaseException {
		boolean flag = service.deleteEmployee(id);
		if (flag) {

			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
		} else
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
	}
	

	// Added a bug to test FindBugs Plugin	
		public String execute() {
	        String name = null;
	        System.out.println("This is the output from excecte : " + name);
	        return name.toLowerCase();
	        
	    }
	
	
}
