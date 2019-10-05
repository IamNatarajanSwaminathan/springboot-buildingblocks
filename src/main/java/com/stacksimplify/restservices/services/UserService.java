package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

// Service
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// To get all the Users...
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	
	// Create a User...
	
	public User createUser(User user) throws UserExistsException{
		User existingUser = userRepository.findByUserName(user.getUserName());
		
		if (existingUser != null) {
			throw new UserExistsException("User already exists in repository");
		}
		return userRepository.save(user);
	}
	
	// get a user based on userID
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent()) {
			String message = "User not found in user Repository";
			throw new UserNotFoundException(message);
		}
		return user;
	}
	
	// Update user by id
	
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (!optionalUser.isPresent()) {
			String message = "User not found in user Repository.  Provide correct ID...";
			throw new UserNotFoundException(message);
		}
		user.setUserId(id);
		return userRepository.save(user);
	}
	
	// Delete User by id
	
	public void deleteUserById(Long id) {
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (!optionalUser.isPresent()) {
			String message = "User not found in user Repository.  Provide correct ID...";
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
		}
		userRepository.deleteById(id);
		
		/*
		 * if (userRepository.findById(id).isPresent()) { userRepository.deleteById(id);
		 * }
		 */
	}
	
	public User getUserNamebyusername(String userName) {
		return userRepository.findByUserName(userName);
	}
}
