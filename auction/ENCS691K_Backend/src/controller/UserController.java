package controller;

import model.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import utility.Strings;
import contract.ReturnMessage;
import db.UserRepository;

@RestController
public class UserController {

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ReturnMessage register(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		if (!Strings.isNull(username) && !Strings.isNull(password)) {
			User user = new User(username, password);
			return UserRepository.Save(user);
		}
		ReturnMessage msg = new ReturnMessage();
		msg.invalidUsernameOrPassword();
		return msg;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public boolean login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		if (!Strings.isNull(username) && !Strings.isNull(password)) {
			User user = UserRepository.get(username);
			return password.equalsIgnoreCase(user.getPassword());
		}
		return false;
	}

	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public boolean close(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		if (!Strings.isNull(username) && !Strings.isNull(password)) {
			return UserRepository.close(username, password);
		}
		return false;
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
