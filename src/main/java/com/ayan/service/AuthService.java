package com.ayan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ayan.exception.SpringRedditException;
import com.ayan.exception.AuthServiceException;
import com.ayan.model.NotificationEmail;
import com.ayan.model.User;
import com.ayan.model.VerificationToken;
import com.ayan.payload.request.RegisterRequest;
import com.ayan.repository.RoleRepository;
import com.ayan.repository.UserRepository;
import com.ayan.repository.VerificationTokenRepository;


import java.util.Optional;
import java.util.UUID;

import static java.time.Instant.now;


@Service(value = "authService")
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private MailService mailService;



	@Transactional
	public Long signup(RegisterRequest registerRequest) throws AuthServiceException {
		
//		verify the user-name and email in the request are not already in use
//		if(userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
//			throw new AuthServiceException("Service.Auth.USERNAME_TAKEN");
//		}
//		if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
//			throw new AuthServiceException("Service.Auth.EMAIL_TAKEN");
//		}
		
//		Create and save new user in database
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setCreated(now());
		user.setEnabled(false);
		
		userRepository.save(user);
		
//		Create and send verification email
		String token = generateVerificationToken(user);
		String message = "Thank you for signing up to Spring Reddit, please click on the below url to activate your account:"; 
//				+ ACTIVATION_EMAIL + "/" + token;
				
		mailService.sendVerificationEmail(new NotificationEmail("Please Activate your account", user.getEmail(), message),token);
		
		return user.getId();
	}
	
	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		
		return token;
	}
	
	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	
	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
		verificationTokenOptional.orElseThrow(() ->
				new SpringRedditException("Invalid token"));
		fetchUserAndEnable(verificationTokenOptional.get());
	}
	
	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		String username = verificationToken.getUser().getUsername();
		
		User user = userRepository.findByUsername(username).orElseThrow(() ->
				new SpringRedditException("User Not found with id - " + username));
		
		user.setEnabled(true);
		
		userRepository.save(user);
	}
	
	@Transactional
	public void deleteAll() {
		userRepository.deleteAll();
		verificationTokenRepository.deleteAll();
		roleRepository.deleteAll();
	}	
}
