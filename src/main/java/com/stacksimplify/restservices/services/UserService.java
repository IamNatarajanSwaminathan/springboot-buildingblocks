package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
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
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	// get a user based on userID
	
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	// Update user by id
	
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	// Delete User by id
	
	public void deleteUserById(Long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	public User getUserNamebyusername(String userName) {
		return userRepository.findByUserName(userName);
	}
}
