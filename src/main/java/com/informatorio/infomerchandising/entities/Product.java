package com.informatorio.infomerchandising.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 200)
	private String description;

	@Column(nullable = false)
	private Double price;

	@Column(length = 1000)
	private String content;

	@Column(nullable = false)
	private Boolean published;

	@JsonIgnore
	@OneToMany(mappedBy = "product", orphanRemoval = true)
	private List<Detail> details;

	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails = new ArrayList<>();

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDate createdAt;

	public Product() {

	}

	public Product(String name, String description, Double price, Boolean published) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.published = published;
		this.details = new ArrayList<>();
	}

	public Product(String name, String description, Double price, String content, Boolean published) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.content = content;
		this.published = published;
		this.details = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

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

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
}
