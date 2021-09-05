package com.informatorio.infomerchandising.controllers;

import com.informatorio.infomerchandising.dtos.UserRequest;
import com.informatorio.infomerchandising.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<?> findAll(
		@RequestParam(required = false) Long city,
		@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate created_at) {

		if (city != null) {
			if (created_at != null)
				return userService.findAllByCityAndCreatedAtAfter(city, created_at);
			return userService.findAllByCity(city);
		}

		return (created_at != null) ?
			userService.findAllByCreatedAtAfter(created_at)
			: userService.findAll();
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> save(@Valid @RequestBody UserRequest request) {
		return userService.save(request);
	}

	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return userService.findById(id);
	}

	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody UserRequest request) {
		return userService.updateById(id, request);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		return userService.deleteById(id);
	}
}
