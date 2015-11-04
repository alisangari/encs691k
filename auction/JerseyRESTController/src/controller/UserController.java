package controller;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Track;
import contract.ReturnMessage;
 
@Path("/user")
public class UserController {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = "Jersey say2 : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	@GET
	@Path("/register")//, method = RequestMethod.POST)
	@Produces(MediaType.APPLICATION_JSON)
	public ReturnMessage register(){/*@RequestParam("username") String username,
			@RequestParam("password") String password) {
		if (!Strings.isNull(username) && !Strings.isNull(password)) {
			User user = new User(username, password);
			return UserRepository.Save(user);
		}*/
		ReturnMessage msg = new ReturnMessage();
		msg.invalidUsernameOrPassword();
		return msg;
	}
	
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrackInJSON() {

		Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");

		return track;

	}


 
}