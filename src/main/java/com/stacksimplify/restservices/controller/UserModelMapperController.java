package com.stacksimplify.restservices.controller;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.dtos.UserMmDto;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")	
	public UserMmDto getUserById(@PathVariable @Min(1) Long id) throws UserNotFoundException{
		Optional<User> optionalUser = userService.getUserById(id);
		
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User Not Found");
		}
		
		User user = optionalUser.get();
		
		UserMmDto userMmDto = modelMapper.map(user,  UserMmDto.class);
		
		return userMmDto;
	}
}
