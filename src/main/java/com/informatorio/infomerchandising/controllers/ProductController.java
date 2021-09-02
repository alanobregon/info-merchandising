package com.informatorio.infomerchandising.controllers;

import com.informatorio.infomerchandising.dtos.ProductRequest;
import com.informatorio.infomerchandising.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findAllPublished(@RequestParam(required = false) String name) {
		return (name != null) ?
			productService.findAllByNameContainingAndPublishedTrue(name)
			: productService.findAllByPublishedTrue();
	}

	@GetMapping(value = "/all")
	@ResponseBody
	public ResponseEntity<?> findAll(@RequestParam(required = false) String name) {
		return (name != null) ?
			productService.findAllByNameContaining(name)
			: productService.findAll();
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> save(@Valid @RequestBody ProductRequest request) {
		return productService.save(request);
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return productService.findById(id);
	}

	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
		return productService.updateById(id, request);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		return productService.deleteById(id);
	}
}
