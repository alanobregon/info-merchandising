package com.informatorio.infomerchandising.repositories;

import com.informatorio.infomerchandising.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	Iterable<?> findAllByPublishedTrue();
	Iterable<?> findAllByNameContaining(String name);
	Iterable<?> findAllByNameContainingAndPublishedTrue(String name);
}