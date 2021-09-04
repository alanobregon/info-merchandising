package com.informatorio.infomerchandising.repositories;

import com.informatorio.infomerchandising.entities.Detail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Long> {}