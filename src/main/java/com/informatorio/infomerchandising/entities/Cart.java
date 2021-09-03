package com.informatorio.infomerchandising.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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

	public Cart() {

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
}
