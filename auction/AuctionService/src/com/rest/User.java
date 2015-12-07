package com.rest;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	// Must have no-argument constructor
	public User() {
	}

	public User(String fname, String lname, String username, String password) {
		this.firstName = fname;
		this.lastName = lname;
		this.username = username;
		this.password = password;
	}

	public void setFirstName(String fname) {
		this.firstName = fname;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lname) {
		this.lastName = lname;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassowrd(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return lastName + ", " + firstName + " (" + username + "/" + password
				+ ")";
	}

}