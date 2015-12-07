package com.auction;

import java.util.ArrayList;

public class UserManagement {
	private static UserManagement instance = null;
	private ArrayList<User> users;

	private UserManagement() {
		this.users = new ArrayList<User>();
	}

	public static UserManagement getInstance() {
		if (instance == null) {
			instance = new UserManagement();
		}
		return instance;
	}

	public boolean registerUser(User newUser) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(newUser.getUsername())) {
				return false;
			}
		}
		newUser = new User(newUser.getName(), newUser.getUsername(),
				newUser.getPassword());
		users.add(newUser);
		return true;
	}

	public boolean isCredentialsValid(String username, String password) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(username)
					&& user.getPassword().equalsIgnoreCase(password)) {
				return true;
			}
		}
		return false;
	}

	public boolean removeUser(String username, String password) {
		if (isCredentialsValid(username, password)) {
			for (User user : users) {
				if (user.getUsername().equalsIgnoreCase(username)
						&& user.getPassword().equalsIgnoreCase(password)) {
					return users.remove(user);
				}
			}
		}
		return false;
	}

	public String toString() {
		return users.toString();
	}

	public User getUser(String username) {
		for(User user: users){
			if(user.getUsername().equalsIgnoreCase(username)){
				return user;
			}
		}
		return null;
	}
}
