package com.ayan.exception;

public class AuthServiceException extends Exception {
	
	private static final long serialVersionUID = 1l;
	
	public AuthServiceException(String message, Exception exception) {
		super(message, exception);
	}
	
	public AuthServiceException(String message) {
		super(message);
	}
}
