package com.informatorio.infomerchandising.services;

import com.informatorio.infomerchandising.dtos.OrderRequest;
import com.informatorio.infomerchandising.entities.*;
import com.informatorio.infomerchandising.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final DetailRepository detailRepository;
	private final OrderDetailRepository orderDetailRepository;

	@Autowired
	public OrderService(UserRepository user, OrderRepository order, DetailRepository detail, OrderDetailRepository orderDetail) {
		this.userRepository = user;
		this.orderRepository = order;
		this.detailRepository = detail;
		this.orderDetailRepository = orderDetail;
	}

	private User findUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	private Order findOrder(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(
			orderRepository.findAll()
		);
	}

	public ResponseEntity<?> findAllFromUser(Long id) {
		var user = findUser(id);
		return (user != null) ?
			ResponseEntity.ok(
				orderRepository.findByUser(user)
			) : new ResponseEntity<>(
				"user not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> save(OrderRequest request) {
		var user = findUser(request.getUser());
		if (user != null) {
			var cart = user.getCart();
			var unpublishedProducts = detailRepository.findByCartAndProduct_PublishedFalse(cart);
			if (unpublishedProducts.isEmpty()) {
				var details = detailRepository.findByCart(cart);
				if (details.isEmpty()) {
					return new ResponseEntity<>(
						"current cart is empty",
						HttpStatus.BAD_REQUEST
					);
				} else {
					var order = orderRepository
						.save(new Order(
							request.getObservation(),
							user
						));

					List<OrderDetail> orderDetails = new ArrayList<>() {{
						details.forEach((detail -> {
							add(new OrderDetail(
								detail.getProduct().getPrice(),
								detail.getQuantity(),
								detail.getProduct(),
								order
							));
						}));
					}};

					orderDetailRepository.saveAll(orderDetails);
					order.setOrderDetails(orderDetails);

					detailRepository.deleteAll(details);
					return ResponseEntity.ok(order);
				}
			} else {
				return new ResponseEntity<>(
					"you have unpublished products",
					HttpStatus.BAD_REQUEST
				);
			}
		}

		return new ResponseEntity<>(
			"user not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> findById(Long id) {
		var order = findOrder(id);
		return (order != null) ?
			ResponseEntity.ok(
				order
			) : new ResponseEntity<>(
				"order not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> updateById(Long id, OrderRequest request) {
		var order = findOrder(id);
		if (order != null) {
			if (request.getObservation() != null)
				order.setObservation(request.getObservation());

			return ResponseEntity.ok(
				orderRepository.save(order)
			);
		}

		return new ResponseEntity<>(
			"order not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> deleteById(Long id) {
		var order = findOrder(id);
		if (order != null) {
			orderRepository.delete(order);
			return ResponseEntity.ok(
				new HashMap<String, Order>() {{
					put("order deleted", order);
				}}
			);
		}

		return new ResponseEntity<>(
			"order not found",
			HttpStatus.NOT_FOUND
		);
	}
}
