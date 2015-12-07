package com.auction;

import java.util.ArrayList;

import utility.IdGenerator;

public class User {
	private int id;
	private String name;
	private String username;
	private String password;

	private User() {
	}

	public User(String name, String username, String password) {
		this.id = IdGenerator.getInstance().getNextIncrementalId();
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "id: " + id + ", name: " + name + ", username: " + username
				+ ", password: " + password;
	}
}
