package com.informatorio.infomerchandising.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_id", updatable = false, nullable = false)
	private User user;

	@Transient
	private Double total;

	@OneToMany(mappedBy = "cart", orphanRemoval = true)
	private List<Detail> details;

	public Cart() {
		this.details = new ArrayList<>();
	}

	public Cart(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getTotal() {
		total = 0.0;
		for (Detail d: details) {
			total += d.getSubtotal();
		}

		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Detail> details) {
		this.details = details;
	}
}
