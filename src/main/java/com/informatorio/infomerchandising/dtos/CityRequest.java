package com.informatorio.infomerchandising.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class CityRequest {

	@NotBlank
	@Length(min = 3, max = 200)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
