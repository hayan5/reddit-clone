package com.ayan.exception;

public class SubredditNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1l;
	
	public SubredditNotFoundException(String message) {
		super(message);
	}

}
