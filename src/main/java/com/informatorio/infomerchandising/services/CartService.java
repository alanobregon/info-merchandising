package com.informatorio.infomerchandising.services;

import com.informatorio.infomerchandising.dtos.DetailRequest;
import com.informatorio.infomerchandising.entities.Cart;
import com.informatorio.infomerchandising.entities.Detail;
import com.informatorio.infomerchandising.entities.Product;
import com.informatorio.infomerchandising.repositories.CartRepository;
import com.informatorio.infomerchandising.repositories.DetailRepository;
import com.informatorio.infomerchandising.repositories.ProductRepository;
import com.informatorio.infomerchandising.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService {

	private final CartRepository cartRepository;
	private final UserRepository userRepository;
	private final DetailRepository detailRepository;
	private final ProductRepository productRepository;

	@Autowired
	public CartService(CartRepository cart, UserRepository user, DetailRepository detail, ProductRepository product) {
		this.cartRepository = cart;
		this.userRepository = user;
		this.detailRepository = detail;
		this.productRepository = product;
	}

	private Cart findCartByUserId(Long id) {
		var user = userRepository.findById(id).orElse(null);
		return (user != null) ?
			cartRepository.findByUser(user)
			: null;
	}

	private Product findProductById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	private Detail findDetailByCartAndProduct(Cart cart, Product product) {
		return detailRepository
			.findByCartAndProduct(cart, product)
			.orElse(null);
	}

	private ResponseEntity<?> updateProductQuantity(Detail detail, Long quantity) {
		detail.setQuantity(quantity);
		return ResponseEntity.ok(
			detailRepository.save(detail)
		);
	}

	public ResponseEntity<?> findCartByUser(Long id) {
		var cart = findCartByUserId(id);
		return (cart != null) ?
			ResponseEntity.ok(cart)
			: new ResponseEntity<>(
				"user not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> addOrUpdateProductToCart(Long user, DetailRequest request) {
		var cart = findCartByUserId(user);
		var product = findProductById(request.getProduct());
		if (cart != null) {
			if (product != null) {
				if (product.getPublished()) {
					var detail = findDetailByCartAndProduct(cart, product);
					if (detail != null) {
						return updateProductQuantity(detail, request.getQuantity());
					} else {
						return new ResponseEntity<>(
							detailRepository.save(
								new Detail(
									product,
									cart,
									request.getQuantity()
								)), HttpStatus.CREATED
						);
					}
				} else {
					return new ResponseEntity<>(
						"product is not published",
						HttpStatus.BAD_REQUEST
					);
				}
			} else {
				return new ResponseEntity<>(
					"product not found",
					HttpStatus.NOT_FOUND
				);
			}
		}

		return new ResponseEntity<>(
			"user not found",
			HttpStatus.NOT_FOUND
		);
	}
}
