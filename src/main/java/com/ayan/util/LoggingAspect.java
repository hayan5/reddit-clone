package com.ayan.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

import com.ayan.exception.AuthServiceException;

@Aspect
@Component
public class LoggingAspect {
	
	public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.ayan.service.AuthService.*(..))", throwing = "exception")
	public void logAuthServiceException(AuthServiceException exception) {
		LOGGER.error(exception.getMessage(), exception);
		
	}
}
