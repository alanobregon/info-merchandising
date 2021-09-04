package com.informatorio.infomerchandising.repositories;

import com.informatorio.infomerchandising.entities.Order;
import com.informatorio.infomerchandising.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	Iterable<?> findByUser(User user);
}