package com.informatorio.infomerchandising.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "details")
public class Detail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Cart cart;

	@Column(nullable = false)
	private Long quantity;

	@Transient
	private Double subtotal;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	public Detail() {

	}

	public Detail(Product product, Cart cart, Long quantity) {
		this.product = product;
		this.cart = cart;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getSubtotal() {
		return (product.getPrice() * quantity);
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
