package com.informatorio.infomerchandising.repositories;

import com.informatorio.infomerchandising.entities.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {}