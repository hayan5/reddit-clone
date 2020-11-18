package com.ayan.payload.response;

import org.springframework.http.HttpStatus;

public class MessageResponse {
	private String message;
	private HttpStatus status;

	public MessageResponse(String message, HttpStatus status) {
	    this.message = message;
	    this.status = status;
	}
	
	public MessageResponse(String message) {
	    this.message = message;
	}
	
	public MessageResponse(HttpStatus status) {
	    this.status = status;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
}