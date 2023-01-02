package com.crud.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AllExceptionHandler extends ResponseEntityExceptionHandler  {
	
	
	
	@ExceptionHandler(UsersNotFoundException.class)

	 String response(UsersNotFoundException e)
	{
		
		return e.getMessage(); // new ResponseEntity<>("Id not Exits",HttpStatus.NOT_FOUND);
	}

}
