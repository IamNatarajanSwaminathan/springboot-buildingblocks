package com.stacksimplify.restservices.Hello;

public class UserDetails {
	
	private String firstName;
	private String lastName;
	private String city;
	
	public UserDetails(String firstName, String lastName,
			String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	@Override
	public String toString() {
		return "UserDetails "
				+ "[firstName=" + firstName + ", "
						+ "lastName=" + lastName + ", "
								+ "city=" + city + "]";
	}
	
	
	
	
	
	
	
}
