package com.stacksimplify.restservices.exceptions;

import java.util.Date;

// Simple Custom error details bean
public class CustomErrorDetails {

	private Date timeStamp;
	private String message;
	private String errorDetails;
	
	public CustomErrorDetails(Date timeStamp, String message, String errorDetails) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.errorDetails = errorDetails;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getErrorDetails() {
		return errorDetails;
	}
	
}
