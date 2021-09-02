package com.informatorio.infomerchandising.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProductRequest {

	@NotBlank
	@Length(min = 5, max = 50)
	private String name;

	@NotBlank
	@Length(min = 20, max = 200)
	private String description;

	@NotNull
	@Positive
	private Double price;

	@Length(min = 100, max = 1000)
	private String content;

	@NotNull
	private Boolean published;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
}
