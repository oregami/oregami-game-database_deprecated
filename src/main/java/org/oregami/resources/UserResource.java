package org.oregami.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.oregami.data.UserDao;
import org.oregami.entities.user.User;

import com.google.inject.Inject;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	private UserDao userDao;

	@POST
	public Response createUser(User user) {
		try {
			userDao.save(user);
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(javax.ws.rs.core.Response.Status.CONFLICT).build();
		}

	}
	
	
	@GET
	public List<User> getUserlist() {
		List<User> findAll = userDao.findAll();
		return findAll;
	}


}
