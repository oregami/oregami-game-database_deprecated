package org.oregami.resources;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.oregami.data.GamingEnvironmentDao;
import org.oregami.entities.GamingEnvironment;

import javax.persistence.OptimisticLockException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/gamingEnvironments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GamingEnvironmentResource {

	@Inject
	public GamingEnvironmentDao dao;
	
	@GET
	public List<GamingEnvironment> list() {
		List<GamingEnvironment> ret = null;
		ret = dao.findAll();
		return ret;
	}
	
	@POST
	public Response addGamingEnvironment(GamingEnvironment newGamingEnvironment) {
		dao.save(newGamingEnvironment);
		return Response.status(Response.Status.CREATED).entity(newGamingEnvironment).build();
	}
	
	
	@PUT
	@Path("{id}")
	public Response updateGame(@PathParam("id") long id, GamingEnvironment updatedGamingEnvironment) {
		if (updatedGamingEnvironment.getId()==null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.update(updatedGamingEnvironment);
		} catch (OptimisticLockException e) {
			Logger.getLogger(this.getClass()).warn("OptimisticLockException", e);
			return Response.status(Response.Status.BAD_REQUEST).tag("OptimisticLockException").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity(updatedGamingEnvironment).build();
	}	


    @GET
    @Path("/{id}")
	public Response getGamingEnvironment(@PathParam("id") String id) {
    	GamingEnvironment gameTite = dao.findOne(id);
    	if (gameTite!=null) {
    		return Response.ok(gameTite).build();
    	} else {
    		return Response.status(Response.Status.NOT_FOUND).build();
    	}
	}

	@GET
	@Path("/{id}/revisions")
	public Response getRevisions(@PathParam("id") String id) {
		return ResourceHelper.getRevisions(id, dao);
	}

	@GET
	@Path("/{id}/revisions/{revision}")
	public Response getRevision(@PathParam("id") String id, @PathParam("revision") String revision) {
		return ResourceHelper.getRevision(id, revision, dao);
	}
	
}
