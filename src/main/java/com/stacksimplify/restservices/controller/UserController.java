package com.stacksimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {		
		return userService.createUser(user);
	}
	
	@GetMapping("/getUserById/{id}")	
	public Optional<User> getUserById(@PathVariable Long id){
		return userService.getUserById(id);
	}
	
	@PutMapping("/updateUserById/{id}")
	public User updateUserById(Long id, @RequestBody User user) {
		return userService.updateUserById(id, user);
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}
	
	@GetMapping("/getUsernameByUsername/{userName}")
	public User getUserByUsername(@PathVariable("userName") String userName) {
		return userService.getUserNamebyusername(userName);
	}
	
	
	
}
