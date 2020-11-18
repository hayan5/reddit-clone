package com.ayan.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ayan.exception.AuthServiceException;
import com.ayan.payload.request.LoginRequest;
import com.ayan.payload.request.RegisterRequest;
import com.ayan.payload.response.JwtResponse;
import com.ayan.payload.response.MessageResponse;
import com.ayan.security.jwt.JwtUtils;
import com.ayan.service.AuthService;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private AuthService authService;
	@Autowired 
	private Environment environment;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
		try {
			Long userId = authService.registerUser(registerRequest);
			
			String msg = environment.getProperty("API_AUTH_REGISTER_SUCCESS") + ":" + userId;
			logger.info(msg);
			
			return ResponseEntity
					.ok()
					.body(new MessageResponse(msg, HttpStatus.OK));
			
		} catch (AuthServiceException e) {
			logger.info(e.getMessage());
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(e.getMessage(), HttpStatus.BAD_REQUEST));
		}
	}
	
	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> verifiyAccount(@PathVariable String token) {
		authService.verifyAccount(token);
		return new ResponseEntity<String>("Account Activated Successfully", HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll() {
		authService.deleteAll();
		return new ResponseEntity<String>("Delete complete", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		JwtResponse jwtResponse = authService.loginUser(loginRequest);
		return ResponseEntity.ok(jwtResponse);
	}
}
