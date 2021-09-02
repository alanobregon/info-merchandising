package com.informatorio.infomerchandising.services;

import com.informatorio.infomerchandising.dtos.UserRequest;
import com.informatorio.infomerchandising.entities.City;
import com.informatorio.infomerchandising.entities.User;
import com.informatorio.infomerchandising.repositories.CityRepository;
import com.informatorio.infomerchandising.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

	private City findCityById(Long id) {
		return cityRepository.findById(id).orElse(null);
	}

	private Boolean emailExists(String email) {
		for (var user : findAll()) {
			if (user instanceof User) {
				if (((User) user).getEmail().equalsIgnoreCase(email))
					return true;
			}
		}

		return false;
	}

	private Boolean passwordLengthValidation(String password) {
		return password.length() < 8 || password.length() > 150;
	}

	public Iterable<?> findAll() {
		return userRepository.findAll();
	}

	public Iterable<?> findAllByCity(Long id) {
		var city = findCityById(id);
		if (city != null)
			return userRepository.findAllByCity(city);
		return null; // Throw exception - city 404
	}

	public Iterable<?> findAllByCreatedAtAfter(LocalDate localDate) {
		return userRepository.findAllByCreatedAtAfter(localDate);
	}

	public Iterable<?> findAllByCityAndCreatedAtAfter(Long id, LocalDate localDate) {
		var city = findCityById(id);
		if (city != null)
			return userRepository.findAllByCityAndCreatedAtAfter(city, localDate);
		return null; // Throw exception - city 404
	}

	public User save(UserRequest request) {
		var city = findCityById(request.getCity());
		if (city != null) {
			if (emailExists(request.getEmail()))
				return null; // Throw exception - email already exists

			return userRepository.save(new User(
				request.getFirstname(),
				request.getLastname(),
				request.getEmail(),
				request.getPassword(),
				city
			));
		}

		return null; // Throw exception - city 404
	}

	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User updateById(Long id, UserRequest request) {
		var user = findById(id);
		if (user != null) {
			if (request.getFirstname() != null)
				user.setFirstname(request.getFirstname());

			if (request.getLastname() != null)
				user.setLastname(request.getLastname());

			if (request.getEmail() != null) {
				if (emailExists(request.getEmail()))
					return null; // Throw exception - email already exists
				user.setEmail(request.getEmail());
			}

			if (request.getPassword() != null) {
				if (passwordLengthValidation(request.getPassword()))
					return null; // Throw exception - password min char length
				user.setPassword(request.getPassword());
			}

			if (request.getCity() != null) {
				var city = findCityById(request.getCity());
				if (city == null)
					return null; // Throw exception - city 404
				user.setCity(city);
			}

			userRepository.save(user);
		}

		return user;
	}

	public User deleteById(Long id) {
		var user = findById(id);
		if (user != null)
			userRepository.delete(user);
		return user;
	}
}
