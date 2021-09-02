package com.informatorio.infomerchandising.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class UserRequest {

	@NotBlank
	@Length(min = 3, max = 30)
	private String firstname;

	@NotBlank
	@Length(min = 3, max = 30)
	private String lastname;

	@Email
	@NotBlank
	@Length(max = 254)
	private String email;

	@NotBlank
	@Length(min = 8, max = 150)
	private String password;

	@NotNull
	@Positive
	private Long city;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}
}
