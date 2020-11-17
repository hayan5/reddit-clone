package com.ayan.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ayan.exception.AuthServiceException;



@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
	
	private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);
	
	@Autowired
	private Environment environment;
	
	@ExceptionHandler(AuthServiceException.class)
	public ResponseEntity<ErrorInfo> handleAuthServiceException(AuthServiceException exception) {
		LOGGER.error(environment.getProperty(exception.getMessage()), exception);
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
		return new ResponseEntity<> (errorInfo, HttpStatus.BAD_REQUEST);
	}
}
