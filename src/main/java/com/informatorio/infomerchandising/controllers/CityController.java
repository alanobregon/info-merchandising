package com.informatorio.infomerchandising.controllers;

import com.informatorio.infomerchandising.dtos.CityRequest;
import com.informatorio.infomerchandising.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

	private final CityService cityService;

	@Autowired
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam(required = false) String name) {
		return (name != null) ?
			cityService.findAllByNameContaining(name)
			: cityService.findAll();
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody CityRequest request) {
		return cityService.save(request);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return cityService.findById(id);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody CityRequest request) {
		return cityService.updateById(id, request);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		return cityService.deleteById(id);
	}
}
