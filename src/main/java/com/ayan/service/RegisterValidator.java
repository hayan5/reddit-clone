package com.ayan.service;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.ayan.exception.AuthServiceException;
import com.ayan.payload.request.RegisterRequest;

@Component
public class RegisterValidator {
	
	public void validate(RegisterRequest request) throws AuthServiceException {
		
		if (!validateUsername(request.getUsername()))
			throw new AuthServiceException("Invalid Username");
		
		if (!validateEmail(request.getEmail())) {
			throw new AuthServiceException("Invalid Email");
		}
		
		if (!validatePassword(request.getPassword())) {
			throw new AuthServiceException("Invalid Password");
		}
		
		Set<String> roles = request.getRole();
		if (!validateRole(roles)) {
			throw new AuthServiceException("Invalid Role");
		}
		
	}
	// Min 3 Max 20
	private boolean validateUsername(String username) {
		int length = username.length();
		if (length < 3 || length > 20)
			return false;
		return true;
	}
	
	// Max 50
	private boolean validateEmail(String email) {
		int length = email.length();
		if (length > 50)
			return false;
		return true;
	}
	// Min 6 Max 40
	private boolean validatePassword(String password) {
		int length = password.length();
		if (length < 6 || length > 40)
			return false;
		return true;
	}
	
	// Must be admin, mod or user
	private boolean validateRole(Set<String> roles) {
		if (roles == null) {
			return false;
		}
		
		for (String role : roles) {
			if (!role.equals("admin") && !role.equals("mod") && !role.equals("user")) {
				return false;
			}
		}
		
		return true;
	}
}
