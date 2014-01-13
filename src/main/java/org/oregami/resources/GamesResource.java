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
import org.oregami.data.DatabaseFiller;
import org.oregami.data.GameDao;
import org.oregami.entities.Game;

import com.google.inject.Inject;


@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GamesResource {

	public GameDao gameRepository;
	
	@Inject
	private DatabaseFiller databaseFiller;
	
	@Inject
	public GamesResource(GameDao gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	  
	@GET
	public List<Game> list() {
		List<Game> ret = null;
		if (gameRepository.findAll().size()==0) {
			getDatabaseFiller().initData();
		}		
		ret = gameRepository.findAll();
		return ret;
	}
	
	@POST
	public Response addGame(Game newGame) {
		System.out.println("post: " + newGame);
		gameRepository.getTransaction().begin();
		gameRepository.save(newGame);
		gameRepository.getTransaction().commit();
		return Response.status(Response.Status.CREATED).entity(newGame).build();
	}
	
	
	@PUT
	public Response updateGame(Game updatedGame) {
		if (updatedGame.getId()==null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			System.out.println("put: " + updatedGame);
			gameRepository.update(updatedGame);
		} catch (OptimisticLockException e) {
			Logger.getLogger(this.getClass()).warn("OptimisticLockException", e);
			return Response.status(Response.Status.BAD_REQUEST).tag("OptimisticLockException").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity(updatedGame).build();
	}	


    @GET
    @Path("/{id}")
	public Response getGame(@PathParam("id") long id) {
    	Game game = gameRepository.findOne(id);
    	if (game!=null) {
    		return Response.ok(game).build();
    	} else {
    		return Response.status(Response.Status.NOT_FOUND).build();
    	}
	}
	
	
	private DatabaseFiller getDatabaseFiller() {
		return databaseFiller;
	}	
	
}
