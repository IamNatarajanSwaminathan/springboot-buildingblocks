package com.stacksimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNameNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	
	// We are going to have a different implementation for createUser method...
	
	/*
	 * @PostMapping("/createUser") public User createUser(@RequestBody User user) {
	 * try { return userService.createUser(user); } catch (UserExistsException ex) {
	 * throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage()); }
	 * }
	 */
	
	@PostMapping("/createUser")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/createUser/{id}")
					.buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@GetMapping("/getUserById/{id}")	
	public Optional<User> getUserById(@PathVariable Long id){
		try {
			return userService.getUserById(id);
		}
		catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					ex.getMessage());
		}
		
	}
	
	@PutMapping("/updateUserById/{id}")
	public User updateUserById(Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		}
		catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					ex.getMessage());
		}		
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}
	
	@GetMapping("/getUsernameByUsername/{userName}")
	public User getUserByUsername(@PathVariable("userName") String userName) 
			throws UserNotFoundException{
		//return userService.getUserNamebyusername(userName);
		
		User user = userService.getUserNamebyusername(userName);
		
		if (user == null) {
			throw new UserNameNotFoundException(
					"Username : '" + userName + "'");
		}
		return user;
	}
	
	
	
}
