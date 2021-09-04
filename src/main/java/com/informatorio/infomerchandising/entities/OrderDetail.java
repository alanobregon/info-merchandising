package com.informatorio.infomerchandising.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private Double price;

	@Column(nullable = false)
	private Long quantity;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Order order;

	@Transient
	private Double subtotal;

	public OrderDetail() {

	}

	public OrderDetail(Double price, Long quantity, Product product, Order order) {
		this.price = price;
		this.quantity = quantity;
		this.product = product;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getSubtotal() {
		subtotal = (price * quantity);
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
}
