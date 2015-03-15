package org.oregami.resources;

import com.google.inject.Inject;
import org.oregami.data.UserDao;
import org.oregami.entities.user.User;
import org.oregami.service.IUserService;
import org.oregami.service.ServiceResult;
import org.oregami.util.MailHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	private UserDao userDao;
	
	@Inject
	private IUserService userService;

	MailHelper mailHelper = MailHelper.instance();
	
	@POST
	public Response createUser(User user) {
		try {
			ServiceResult<User> register = userService.register(user);
			if (register.hasErrors()) {
				return Response.status(Status.BAD_REQUEST)
						//.type("text/plain")
						.type("text/json")
		                .entity(register.getErrors()).build();
			}
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(javax.ws.rs.core.Response.Status.CONFLICT).type("text/plain")
	                .entity(e.getMessage()).build();
		}

	}
	
	
	@GET
	public List<User> getUserlist() {
		List<User> findAll = userDao.findAll();
		return findAll;
	}


}
