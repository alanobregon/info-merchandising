package com.informatorio.infomerchandising.repositories;

import com.informatorio.infomerchandising.entities.City;
import com.informatorio.infomerchandising.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	Iterable<?> findAllByCity(City city);
	Iterable<?> findAllByCreatedAtAfter(LocalDate localDate);
	Iterable<?> findAllByCityAndCreatedAtAfter(City city, LocalDate localDate);
}