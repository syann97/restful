package com.github.syann97.restful.myrestfulservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.syann97.restful.myrestfulservice.bean.User;
import com.github.syann97.restful.myrestfulservice.exception.UserNotFoundException;
import com.github.syann97.restful.myrestfulservice.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

	private UserRepository userRepository;

	public UserJpaController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<EntityModel> retrieveUserById(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("id = " + id);
		}

		EntityModel entityModel = EntityModel.of(user.get());

		WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(linTo.withRel("all-users"));

		return ResponseEntity.ok(entityModel);
	}


	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("id = " + id);
		}
		userRepository.delete(user.get());

		return ResponseEntity.noContent().build();
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();

		return ResponseEntity.created(location).build();
	}
}
