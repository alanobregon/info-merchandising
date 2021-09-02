package com.informatorio.infomerchandising.services;

import com.informatorio.infomerchandising.dtos.CityRequest;
import com.informatorio.infomerchandising.entities.City;
import com.informatorio.infomerchandising.repositories.CityRepository;
import com.informatorio.infomerchandising.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CityService {

	private final CityRepository cityRepository;

	@Autowired
	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	private City findCity(Long id) {
		return cityRepository.findById(id).orElse(null);
	}

	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(
			cityRepository.findAll()
		);
	}

	public ResponseEntity<?> findAllByNameContaining(String name) {
		return ResponseEntity.ok(
			cityRepository.findByNameContaining(name)
		);
	}

	public ResponseEntity<?> save(CityRequest request) {
		return ResponseEntity.ok(
			cityRepository.save(new City(
				request.getName()
			))
		);
	}

	public ResponseEntity<?> findById(Long id) {
		var city = findCity(id);
		return (city != null) ?
			ResponseEntity.ok(
				city
			) : new ResponseEntity<>(
				"city not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> updateById(Long id, CityRequest request) {
		var city = findCity(id);
		if (city != null) {
			if (ValidationUtils.stringLengthValidation(request.getName(), 3, 200)) {
				city.setName(request.getName());
			} else {
				return new ResponseEntity<>(
					"city name must be between 3 and 200 characters",
					HttpStatus.CONFLICT
				);
			}

			return ResponseEntity.ok(
				cityRepository.save(city)
			);
		}

		return new ResponseEntity<>(
			"city not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> deleteById(Long id) {
		var city = findCity(id);
		if (city != null) {
			cityRepository.delete(city);
			return ResponseEntity.ok(
				city
			);
		}

		return new ResponseEntity<>(
			"city not found",
			HttpStatus.NOT_FOUND
		);
	}
}
