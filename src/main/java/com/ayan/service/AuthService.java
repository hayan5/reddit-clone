package com.ayan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ayan.exception.SpringRedditException;
import com.ayan.exception.AuthServiceException;
import com.ayan.model.ERole;
import com.ayan.model.NotificationEmail;
import com.ayan.model.Role;
import com.ayan.model.User;
import com.ayan.model.VerificationToken;
import com.ayan.payload.request.LoginRequest;
import com.ayan.payload.request.RegisterRequest;
import com.ayan.payload.response.JwtResponse;
import com.ayan.repository.RoleRepository;
import com.ayan.repository.UserRepository;
import com.ayan.repository.VerificationTokenRepository;
import com.ayan.security.jwt.JwtUtils;
import com.ayan.security.services.UserDetailsImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.Instant.now;
import static com.ayan.util.Constants.ACTIVATION_EMAIL;


@Service(value = "authService")
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private RegisterValidator validator;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MailService mailService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	
	@Transactional(readOnly = true)
	public User getCurrentUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
		String principal = authentication.getName();
		return userRepository.findByUsername(principal)
				.orElseThrow(() -> new UsernameNotFoundException("User name not found: "+ principal));
	}
	
	public JwtResponse loginUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 roles);
	}


	@Transactional
	public Long registerUser(RegisterRequest registerRequest) throws AuthServiceException {
		
		// Validate register request
		validator.validate(registerRequest);
		
		if (userRepository.existsByUsername(registerRequest.getUsername())) {
			throw new AuthServiceException("Username is taken");
		}
		if (userRepository.existsByEmail(registerRequest.getEmail())) {
			throw new AuthServiceException("Email is already in use");
		}
		
		// Set roles
		Set<String> strRoles = registerRequest.getRole();
		Set<Role> roles = new HashSet<>();
		
		strRoles.forEach(role -> {
			switch(role) {
			case "admin":
				Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Service.Auth.INVALID_ROLE" + ":" + role));
				
				roles.add(adminRole);
				break;
				
			case "mod":
				Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException("Service.Auth.INVALID_ROLE" + ":" + role));
				
				roles.add(modRole);
				break;
				
			case "user":
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Service.Auth.INVALID_ROLE" + ":" + role));
				
				roles.add(userRole);
				
				break;
			}
		});
		
		// Create and save new user
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setRoles(roles);
		user.setCreated(now());
		user.setEnabled(false);
		
		userRepository.save(user);
		
		// Create and send verification email
		String token = generateVerificationToken(user);
		String message = "Thank you for signing up to Spring Reddit, please click on the below url to activate your account:"
				+ ACTIVATION_EMAIL + "/" + token;
		
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
//		roleRepository.deleteAll();
	}	
	
	
	
}
