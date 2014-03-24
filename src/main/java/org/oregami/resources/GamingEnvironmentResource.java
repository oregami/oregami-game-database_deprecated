package org.oregami.resources;

import java.util.List;

import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.oregami.data.GamingEnvironmentDao;
import org.oregami.entities.GamingEnvironment;

import com.google.inject.Inject;


@Path("/gameingEnvironment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GamingEnvironmentResource {

	@Inject
	public GamingEnvironmentDao gamingEnvironmentDao;
	
	@GET
	public List<GamingEnvironment> list() {
		List<GamingEnvironment> ret = null;
		ret = gamingEnvironmentDao.findAll();
		return ret;
	}
	
	@POST
	public Response addGamingEnvironment(GamingEnvironment newGamingEnvironment) {
		System.out.println("post: " + newGamingEnvironment);
		gamingEnvironmentDao.save(newGamingEnvironment);
		return Response.status(Response.Status.CREATED).entity(newGamingEnvironment).build();
	}
	
	
	@PUT
	@Path("{id}")
	public Response updateGame(@PathParam("id") long id, GamingEnvironment updatedGamingEnvironment) {
		if (updatedGamingEnvironment.getId()==null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			gamingEnvironmentDao.update(updatedGamingEnvironment);
		} catch (OptimisticLockException e) {
			Logger.getLogger(this.getClass()).warn("OptimisticLockException", e);
			return Response.status(Response.Status.BAD_REQUEST).tag("OptimisticLockException").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity(updatedGamingEnvironment).build();
	}	


    @GET
    @Path("/{id}")
	public Response getGamingEnvironment(@PathParam("id") long id) {
    	GamingEnvironment gameTite = gamingEnvironmentDao.findOne(id);
    	if (gameTite!=null) {
    		return Response.ok(gameTite).build();
    	} else {
    		return Response.status(Response.Status.NOT_FOUND).build();
    	}
	}
	
}
