package com.app.exceptions;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.app.exceptions.customExceptions.DatabaseException;
import com.app.exceptions.customExceptions.RecordNotFoundException;

@ControllerAdvice(annotations= {RestController.class,Controller.class})
public class ExceptionManager {
	
	 @ExceptionHandler(value = { RecordNotFoundException.class })
	 public ResponseEntity<String> handleRecordNotFoundException(RecordNotFoundException ex){
		 return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(value = DatabaseException.class)
	 public ResponseEntity<String> handleDatabaseException(DataAccessException ex){
		 return new ResponseEntity<String>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	 
	 @ExceptionHandler(value=JDBCConnectionException.class)
	 public ResponseEntity<String> handleDatabaseException(JDBCConnectionException ex){
		return new ResponseEntity<String>("DataBase is not Available!!!. please try again later....."+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		 
	 }
}
