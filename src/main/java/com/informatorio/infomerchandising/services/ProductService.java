package com.informatorio.infomerchandising.services;

import com.informatorio.infomerchandising.dtos.ProductRequest;
import com.informatorio.infomerchandising.entities.Product;
import com.informatorio.infomerchandising.repositories.ProductRepository;
import com.informatorio.infomerchandising.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	private Product findProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(productRepository.findAll());
	}

	public ResponseEntity<?> findAllByPublishedTrue() {
		return ResponseEntity.ok(productRepository.findAllByPublishedTrue());
	}

	public ResponseEntity<?> findAllByNameContaining(String name) {
		return ResponseEntity.ok(productRepository.findAllByNameContaining(name));
	}

	public ResponseEntity<?> findAllByNameContainingAndPublishedTrue(String name) {
		return ResponseEntity.ok(productRepository.findAllByNameContainingAndPublishedTrue(name));
	}

	public ResponseEntity<?> save(ProductRequest request) {
		if (request.getContent() != null)
			return new ResponseEntity<>(
				productRepository.save(new Product(
					request.getName(),
					request.getDescription(),
					request.getPrice(),
					request.getContent(),
					request.getPublished())
				), HttpStatus.CREATED
			);

		return new ResponseEntity<>(
			productRepository.save(new Product(
				request.getName(),
				request.getDescription(),
				request.getPrice(),
				request.getPublished())
			), HttpStatus.CREATED
		);
	}

	public ResponseEntity<?> findById(Long id) {
		var product = findProduct(id);
		return (product != null) ?
			ResponseEntity.ok(product)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> updateById(Long id, ProductRequest request) {
		var product = findProduct(id);
		if (product != null) {
			if (request.getName() != null) {
				if (ValidationUtils.stringLengthValidation(request.getName(), 5, 50)) {
					product.setName(request.getName());
				} else {
					return new ResponseEntity<>("name", HttpStatus.CONFLICT); // Nombre debe estar entre 3 y 50
				}
			}

			if (request.getDescription() != null) {
				if (ValidationUtils.stringLengthValidation(request.getDescription(), 20, 200)) {
					product.setDescription(request.getDescription());
				} else {
					return new ResponseEntity<>("descr", HttpStatus.CONFLICT); // Descripcion debe estar entre 20 y 200
				}
			}

			if (request.getPrice() != null) {
				if (ValidationUtils.positiveNumber(request.getPrice())) {
					product.setPrice(request.getPrice());
				} else {
					return new ResponseEntity<>("price", HttpStatus.CONFLICT); // Precio debe ser positivo
				}
			}

			if (request.getContent() != null) {
				if (ValidationUtils.stringLengthValidation(request.getContent(), 100, 1000)) {
					product.setContent(request.getContent());
				} else {
					return new ResponseEntity<>("content", HttpStatus.CONFLICT); // El contenido debe estar entre 100 y 1000
				}
			}

			if (request.getPublished() != null) {
				product.setPublished(request.getPublished());
			}

			return ResponseEntity.ok(productRepository.save(product));
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> deleteById(Long id) {
		var product = findProduct(id);
		if (product != null) {
			productRepository.delete(product);
			return ResponseEntity.ok(product);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
