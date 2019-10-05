package com.stacksimplify.restservices.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/{id}")	
	public Resource<User> getUserById(@PathVariable @Min(1) Long id){
		try {
			//return userService.getUserById(id);
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			
			Long userid = user.getUserId();	
			Link selflink = ControllerLinkBuilder.linkTo
					(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			Resource<User> finalResource = new Resource<User>(user);
			return finalResource;
		}
		catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					ex.getMessage());
		}		
	}
	
	@GetMapping
	public Resources<User> getAllUsers() {
		//return userService.getAllUsers();
		
		List<User> allUsers = userService.getAllUsers();
		
		for (User user: allUsers) {
			// Selflink
			
			Long userid = user.getUserId();
			Link selflink = ControllerLinkBuilder.linkTo
					(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
			
			// Relationship link with getAllOrders...
			
			try {
				Resources<Order> orders = 
						ControllerLinkBuilder.methodOn
						(OrderHateoasController.class)
						.getAllOrders(userid);
				
				Link ordersLink = ControllerLinkBuilder.linkTo(orders)
						.withRel("all-orders");
				user.add(ordersLink);
				
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		// Self link for get all Users...
		Link selfLinkGetAllUsers =  ControllerLinkBuilder
				.linkTo(this.getClass()).withSelfRel();
		Resources<User> finalResources = new Resources<User>(allUsers, selfLinkGetAllUsers);
		return finalResources;
	}
}
