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
		if (name != null)
			return ResponseEntity.ok(cityService.findAllByNameContaining(name));
		return ResponseEntity.ok(cityService.findAll());
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody CityRequest request) {
		return ResponseEntity.ok(cityService.save(request));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		var data = cityService.findById(id);
		return (data != null) ? ResponseEntity.ok(data) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody CityRequest request) {
		var data = cityService.updateById(id, request);
		return (data != null) ? ResponseEntity.ok(data) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		var data = cityService.deleteById(id);
		return (data != null) ? ResponseEntity.ok(data) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
