package com.niranjan.customExceptions;

public class JapaNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JapaNotFoundException(String message) {
		super(message);
	}
	
}
