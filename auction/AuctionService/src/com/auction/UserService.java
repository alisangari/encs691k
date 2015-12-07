package com.auction;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.coenraets.cellar.Wine;

@Path("/userservice")
public class UserService {

	// @GET
	// @Path("/print/{name}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public User produceJSON(@PathParam("name") String name) {
	// User st = new User(name, "Diaz", "username", "password");
	// return st;
	// }

	// register user
	@POST
	@Path("/register")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean create(User user) {
		System.out.println(user.toString());
		UserManagement userManager = UserManagement.getInstance();
		boolean flag = userManager.registerUser(user);
		System.out.println("user created: " + flag);
		System.out.println(userManager);
		// return dao.create(wine);
		return flag;
	}

	// login
	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean login(User user) {
		System.out.println(user.toString());
		UserManagement userManager = UserManagement.getInstance();
		boolean flag = userManager.isCredentialsValid(user.getUsername(),
				user.getPassword());
		System.out.println("user login: " + flag);
		return flag;
	}

	// close account
	@DELETE
	@Path("/delete/{username}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean delete(@PathParam("username") String username) {
		System.out.println("delete");
		UserManagement userManager = UserManagement.getInstance();
		User user = userManager.getUser(username);
		if (user != null) {
			ProductManagement prodManager = ProductManagement.getInstance();
			if (prodManager.userHasActiveProducts(user.getUsername())) {
				return userManager.removeUser(user.getUsername(),
						user.getPassword());
			}

		}
		return false;
	}

}
