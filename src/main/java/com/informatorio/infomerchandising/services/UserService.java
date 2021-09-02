package com.informatorio.infomerchandising.services;

import com.informatorio.infomerchandising.dtos.UserRequest;
import com.informatorio.infomerchandising.entities.City;
import com.informatorio.infomerchandising.entities.User;
import com.informatorio.infomerchandising.repositories.CityRepository;
import com.informatorio.infomerchandising.repositories.UserRepository;
import com.informatorio.infomerchandising.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

	private final CityRepository cityRepository;
	private final UserRepository userRepository;

	@Autowired
	public UserService(CityRepository cityRepository, UserRepository userRepository) {
		this.cityRepository = cityRepository;
		this.userRepository = userRepository;
	}

	private City findCity(Long id) {
		return cityRepository.findById(id).orElse(null);
	}

	private User findUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	private Boolean emailExists(String email) {
		for (var user : userRepository.findAll())
			if (user.getEmail().equalsIgnoreCase(email))
				return true;
		return false;
	}

	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(
			userRepository.findAll()
		);
	}

	public ResponseEntity<?> findAllByCity(Long id) {
		var city = findCity(id);
		return (city != null) ?
			ResponseEntity.ok(
				userRepository.findAllByCity(city)
			) : new ResponseEntity<>(
				"city not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> findAllByCreatedAtAfter(LocalDate date) {
		return ResponseEntity.ok(
			userRepository.findAllByCreatedAtAfter(date)
		);
	}

	public ResponseEntity<?> findAllByCityAndCreatedAtAfter(Long id, LocalDate date) {
		var city = findCity(id);
		return (city != null) ?
			ResponseEntity.ok(
				userRepository.findAllByCityAndCreatedAtAfter(city, date)
			) : new ResponseEntity<>(
				"city not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> save(UserRequest request) {
		var city = findCity(request.getCity());
		if (city != null) {
			if (emailExists(request.getEmail()))
				return new ResponseEntity<>(
					"email already exists",
					HttpStatus.CONFLICT
				);

			return new ResponseEntity<>(
				userRepository.save(new User(
					request.getFirstname(),
					request.getLastname(),
					request.getEmail(),
					request.getPassword(),
					city
				)), HttpStatus.CREATED
			);
		}

		return new ResponseEntity<>(
			"city not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> findById(Long id) {
		var user = findUser(id);
		return (user != null) ?
			ResponseEntity.ok(
				user
			) : new ResponseEntity<>(
				"user not found",
			HttpStatus.NOT_FOUND
		);
	}

	public ResponseEntity<?> updateById(Long id, UserRequest request) {
		var user = findUser(id);
		if (user != null) {
			if (request.getFirstname() != null) {
				if (ValidationUtils.stringLengthValidation(request.getFirstname(), 3, 30)) {
					user.setFirstname(request.getFirstname());
				} else {
					return new ResponseEntity<>(
						"firstname must be between 3 and 30 characters",
						HttpStatus.CONFLICT
					);
				}
			}

			if (request.getLastname() != null) {
				if (ValidationUtils.stringLengthValidation(request.getLastname(), 3, 30)) {
					user.setLastname(request.getLastname());
				} else {
					return new ResponseEntity<>(
						"lastname must be between 3 and 30 characters",
						HttpStatus.CONFLICT
					);
				}
			}

			if (request.getEmail() != null) {
				if (emailExists(request.getEmail())) {
					return new ResponseEntity<>(
						"email already exists",
						HttpStatus.CONFLICT
					);
				}

				if (ValidationUtils.emailValidation(request.getEmail())) {
					if (ValidationUtils.stringLengthValidation(request.getEmail(), 254)) {
						user.setEmail(request.getEmail());
					} else {
						return new ResponseEntity<>(
							"email should not exceed 254 characters",
							HttpStatus.CONFLICT
						);
					}
				} else {
					return new ResponseEntity<>(
						"email format is invalid",
						HttpStatus.CONFLICT
					);
				}
			}

			if (request.getPassword() != null) {
				if (ValidationUtils.stringLengthValidation(request.getPassword(), 8, 150)) {
					user.setPassword(request.getPassword());
				} else {
					return new ResponseEntity<>(
						"password must be between 8 and 150 characters",
						HttpStatus.CONFLICT
					);
				}
			}

			if (request.getCity() != null) {
				var city = findCity(request.getCity());
				if (city != null) {
					user.setCity(city);
				} else {
					return new ResponseEntity<>(
						"city not found",
						HttpStatus.NOT_FOUND
					);
				}

				return ResponseEntity.ok(
					userRepository.save(user)
				);
			}
		}

		return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> deleteById(Long id) {
		var user = findUser(id);
		if (user != null) {
			userRepository.delete(user);
			return ResponseEntity.ok(user);
		}

		return new ResponseEntity<>(
			"user not found",
			HttpStatus.NOT_FOUND
		);
	}
}
