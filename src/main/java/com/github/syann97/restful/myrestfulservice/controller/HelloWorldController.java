package com.github.syann97.restful.myrestfulservice.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.github.syann97.restful.myrestfulservice.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
	private MessageSource messageSource;

	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World!";
	}

	@GetMapping("hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World!");
	}

	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World! %s", name));
	}

	@GetMapping("hello-world-internationalized")
	public String helloWorldInternationalized(
		@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("greeting.message", null, locale);
	}
}
