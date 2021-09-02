package com.informatorio.infomerchandising.repositories;

import com.informatorio.infomerchandising.entities.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
	Iterable<?> findByNameContaining(String name);
}