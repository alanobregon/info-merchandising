package com.informatorio.infomerchandising.controllers;

import com.informatorio.infomerchandising.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users/{id}/cart")
public class CartController {

	public final CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping
	public ResponseEntity<?> findByUser(@PathVariable("id") Long id) {
		return cartService.findByUser(id);
	}
}
