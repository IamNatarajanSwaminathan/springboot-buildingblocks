package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// This is the controller
@RestController
public class HelloWorldController {

	// Simple Method...
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld1")
	public String HelloWorld() {
		return "My First Rest Controller in Spring Boot";
	}
	
	@GetMapping("/getUser")
	public UserDetails helloWorldBean() {
		return new UserDetails("Nat", "Swa", "Chennai");
	}
}
