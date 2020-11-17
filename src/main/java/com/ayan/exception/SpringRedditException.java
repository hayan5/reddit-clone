package com.ayan.exception;

public class SpringRedditException extends RuntimeException{
	
	private static final long serialVersionUID = 1l;
	public SpringRedditException(String message, Exception e) {
		super(message, e);
	}
	public SpringRedditException(String message) {
		super(message);
	}
}
