package com.github.syann97.restful.myrestfulservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.syann97.restful.myrestfulservice.bean.User;
import com.github.syann97.restful.myrestfulservice.repository.UserRepository;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

	private UserRepository userRepository;

	public UserJpaController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
