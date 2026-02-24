package com.niranjan.globalExceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.niranjan.customExceptions.JapaNotFoundException;
import com.niranjan.customExceptions.UserAlreadyExistException;
import com.niranjan.customExceptions.UserNotFoundException;

import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public String UserNotFoundExceptionHandler(UserNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public String UserAlreadyExistExceptionHandler(UserAlreadyExistException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(JapaNotFoundException.class)
	public String JapaNotFoundExceptionHandler(JapaNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(SignatureException.class)
	public String UserAlreadyExistExceptionHandler(SignatureException ex) {
		return ex.getMessage();
	}
	
}
