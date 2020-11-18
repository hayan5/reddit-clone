package com.ayan.payload.request;


import java.util.Set;

import javax.validation.constraints.*;

public class RegisterRequest {
	@NotBlank(message = "{registerRequest.username.absent}")
//	@Size(min=3, max=20)
	private String username;
	
	@NotBlank(message = "{registerRequest.email.absent}")
	@Size(max=50)
	@Email(message = "{registerRequest.email.invalid}")
	private String email;
	
	@NotBlank(message = "{registerRequest.password.absent}")
	@Size(min=6, max=40)
	private String password;
	
	private Set<String> role;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
}


