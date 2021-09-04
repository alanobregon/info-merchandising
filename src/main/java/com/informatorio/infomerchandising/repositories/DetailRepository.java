package com.informatorio.infomerchandising.repositories;

import com.informatorio.infomerchandising.entities.Cart;
import com.informatorio.infomerchandising.entities.Detail;
import com.informatorio.infomerchandising.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Long> {
	Optional<Detail> findByCartAndProduct(Cart cart, Product product);
}