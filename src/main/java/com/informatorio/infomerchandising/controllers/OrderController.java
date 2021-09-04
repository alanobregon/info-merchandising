package com.informatorio.infomerchandising.controllers;

import com.informatorio.infomerchandising.dtos.OrderRequest;
import com.informatorio.infomerchandising.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	private final OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findAll(@RequestParam(required = false) Long user) {
		return (user != null) ?
			orderService.findAllFromUser(user)
			: orderService.findAll();
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderRequest request) {
		return orderService.save(request);
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> findOrderById(@PathVariable("id") Long id) {
		return orderService.findById(id);
	}

	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> updateOrderById(@PathVariable("id") Long id, @RequestBody OrderRequest request) {
		return orderService.updateById(id, request);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteOrderById(@PathVariable("id") Long id) {
		return orderService.deleteById(id);
	}
}
