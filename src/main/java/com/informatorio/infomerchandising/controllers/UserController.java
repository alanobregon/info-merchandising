package com.informatorio.infomerchandising.controllers;

import com.informatorio.infomerchandising.dtos.UserRequest;
import com.informatorio.infomerchandising.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<?> findAll(
		@RequestParam(required = false) Long city,
		@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate created_at) {

		if (city != null) {
			if (created_at != null) {
				var findByCityAndDateAfter = userService.findAllByCityAndCreatedAtAfter(city, created_at);
				return (findByCityAndDateAfter != null) ?
					ResponseEntity.ok(findByCityAndDateAfter)
					: new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			var findByCity = userService.findAllByCity(city);
			return (findByCity != null) ?
				ResponseEntity.ok(findByCity)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return (created_at != null) ?
			ResponseEntity.ok(userService.findAllByCreatedAtAfter(created_at))
			: ResponseEntity.ok(userService.findAll());
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody UserRequest request) {
		var user = userService.save(request);
		return (user != null) ?
			ResponseEntity.ok(user)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		var findUserById = userService.findById(id);
		return (findUserById != null) ?
			ResponseEntity.ok(findUserById)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody UserRequest request) {
		var updateUserById = userService.updateById(id, request);
		return (updateUserById != null) ?
			ResponseEntity.ok(updateUserById)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		var deleteUserById = userService.deleteById(id);
		return (deleteUserById != null) ?
			ResponseEntity.ok(deleteUserById)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
