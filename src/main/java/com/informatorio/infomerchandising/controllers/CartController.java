package com.informatorio.infomerchandising.controllers;

import com.informatorio.infomerchandising.dtos.DetailRequest;
import com.informatorio.infomerchandising.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users/{id}/cart")
public class CartController {

	private final CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findByUser(@PathVariable("id") Long id) {
		return cartService.findCartByUser(id);
	}

	@PutMapping
	@ResponseBody
	public ResponseEntity<?> addOrUpdateProductToCart(@PathVariable("id") Long id, @Valid @RequestBody DetailRequest request) {
		return cartService.addOrUpdateProductToCart(id, request);
	}

	@DeleteMapping(value = "/{product}")
	@ResponseBody
	public ResponseEntity<?> removeProductToCart(@PathVariable("id") Long id, @PathVariable("product") Long product) {
		return cartService.removeProductToCart(id, product);
	}
}
