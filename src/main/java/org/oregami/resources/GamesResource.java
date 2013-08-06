package org.oregami.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.oregami.data.DatabaseFiller;
import org.oregami.data.GameDao;
import org.oregami.entities.Game;

import com.google.inject.Inject;


@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GamesResource {

	public GameDao gameRepository;
	
	@Inject
	private DatabaseFiller databaseFiller;
	
	@Inject
	public GamesResource(GameDao gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	  
	@GET
	@Path("/list")
	public List<Game> list() {
		List<Game> ret = null;
		gameRepository.getTransaction().begin();
		ret = gameRepository.findAll();
		gameRepository.getTransaction().commit();
		return ret;
	}
	
	
	@GET
	@Path("/init")
	public String init() {
		String ret = "data was already there";
		gameRepository.getTransaction().begin();
		Game game = null;
		game = gameRepository.findOne(1L);
		if (game==null) {
			getDatabaseFiller().initData();
			ret = "data initialized";
		}
		gameRepository.getTransaction().commit();
		
		return ret;
	}


	private DatabaseFiller getDatabaseFiller() {
		return databaseFiller;
	}	
	
}
