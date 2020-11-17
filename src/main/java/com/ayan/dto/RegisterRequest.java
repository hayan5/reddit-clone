package com.ayan.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class RegisterRequest {
	
	@NotNull(message = "{registerRequest.username.absent}")
	private String username;
	
	@NotNull(message = "{registerRequest.email.absent}")
	@Email(message = "{registerRequest.email.invalid}")
	private String email;
	
	@NotNull(message = "{registerRequest.password.absent}")
	private String password;
	
//	Constructors
	public RegisterRequest() {
		super();
	}
	public RegisterRequest(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
//	Getter Setter
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
	
//	Equals HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegisterRequest other = (RegisterRequest) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
//	To String
	@Override
	public String toString() {
		return "RegisterRequest [username=" + username + ", email=" + email + ", password=" + password + "]";
	}
}
