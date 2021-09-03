package com.informatorio.infomerchandising.repositories;

import com.informatorio.infomerchandising.entities.Cart;
import com.informatorio.infomerchandising.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
	Cart findByUser(User user);
}