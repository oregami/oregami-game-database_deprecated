package org.oregami.resources;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import org.oregami.data.DatabaseFiller;
import org.oregami.data.GameTitleDao;
import org.oregami.entities.GameTitle;

import javax.persistence.OptimisticLockException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/gameTitle")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameTitleResource {

	@Inject
	public GameTitleDao gameTitleDao;
	
	@GET
	public List<GameTitle> list() {
		List<GameTitle> ret = null;
//		if (gameTitleDao.findAll().size()==0) {
//			getDatabaseFiller().initData();
//		}		
		ret = gameTitleDao.findAll();
		return ret;
	}
	
	@POST
	public Response addGameTitle(GameTitle newGameTitle) {
		System.out.println("post: " + newGameTitle);
		gameTitleDao.save(newGameTitle);
		return Response.status(Response.Status.CREATED).entity(newGameTitle).build();
	}
	
	
	@PUT
	@Path("{id}")
	public Response updateGame(@PathParam("id") String id, GameTitle updatedGameTitle) {
		if (updatedGameTitle.getId()==null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			gameTitleDao.update(updatedGameTitle);
		} catch (OptimisticLockException e) {
			Logger.getLogger(this.getClass()).warn("OptimisticLockException", e);
			return Response.status(Response.Status.BAD_REQUEST).tag("OptimisticLockException").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity(updatedGameTitle).build();
	}	


    @GET
    @Path("/{id}")
	public Response getGameTitle(@PathParam("id") String id) {
    	GameTitle gameTite = gameTitleDao.findOne(id);
    	if (gameTite!=null) {
    		return Response.ok(gameTite).build();
    	} else {
    		return Response.status(Response.Status.NOT_FOUND).build();
    	}
	}
	
	
	private DatabaseFiller getDatabaseFiller() {
		return DatabaseFiller.getInstance();
	}	
	
}
