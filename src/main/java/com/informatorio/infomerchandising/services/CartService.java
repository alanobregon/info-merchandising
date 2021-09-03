package com.informatorio.infomerchandising.services;

import com.informatorio.infomerchandising.entities.User;
import com.informatorio.infomerchandising.repositories.CartRepository;
import com.informatorio.infomerchandising.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CartService {

	private final CartRepository cartRepository;
	private final UserRepository userRepository;

	@Autowired
	public CartService(CartRepository cart, UserRepository user) {
		this.cartRepository = cart;
		this.userRepository = user;
	}

	private User findUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public ResponseEntity<?> findByUser(Long id) {
		var user = findUser(id);
		return (user != null) ?
			ResponseEntity.ok(
				cartRepository.findByUser(user)
			) : new ResponseEntity<>(
				"user not found",
			HttpStatus.NOT_FOUND
		);
	}
}
