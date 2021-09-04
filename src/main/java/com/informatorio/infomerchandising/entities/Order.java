package com.informatorio.infomerchandising.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 200)
	private String observation;

	@OneToMany(mappedBy = "order", orphanRemoval = true)
	private List<OrderDetail> orderDetails = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private User user;

	@Transient
	private Double total;

	@CreationTimestamp
	private LocalDateTime createdAt;

	public Order() {

	}

	public Order(String observation, User user) {
		this.observation = observation;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getTotal() {
		total = 0.0;
		for (OrderDetail o : orderDetails)
			total += o.getSubtotal();
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
