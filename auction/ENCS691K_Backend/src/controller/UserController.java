package controller;

import model.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import utility.Strings;
import db.UserRepository;

@RestController
public class UserController {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		if (!Strings.isNull(username) && !Strings.isNull(password)) {
			User user = new User(username, password);
			UserRepository.Save(user);
		}
	}

	@RequestMapping("/get")
	public User get(
			@RequestParam(value = "username", defaultValue = "ali") String username) {
		return UserRepository.get(username);
	}

	@RequestMapping("/delete")
	public void delete(
			@RequestParam(value = "username", defaultValue = "ali") String username) {
		UserRepository.delete(username);
	}
}
