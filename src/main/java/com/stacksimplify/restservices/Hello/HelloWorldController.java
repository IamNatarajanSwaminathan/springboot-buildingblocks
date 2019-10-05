package com.stacksimplify.restservices.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// This is the controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;

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
	
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat
		(@RequestHeader(name="Accept-Language",required=false) String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}
	
	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat() {
		return messageSource.getMessage("label.hello", null, 
				LocaleContextHolder.getLocale());
	}
}
