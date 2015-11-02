package controller;

import model.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import utility.Strings;
import contract.ReturnMessage;
import db.UserRepository;

@RestController
public class UserController {

	@RequestMapping("/register")
	// , method = RequestMethod.POST)
	public ReturnMessage register(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		System.out.println("in registration ********");
		if (!Strings.isNull(username) && !Strings.isNull(password)) {
			User user = new User(username, password);
			return UserRepository.Save(user);
		}
		ReturnMessage msg = new ReturnMessage();
		msg.invalidUsernameOrPassword();
		return msg;
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
