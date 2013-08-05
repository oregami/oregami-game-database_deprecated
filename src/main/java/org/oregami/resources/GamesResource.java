package org.oregami.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.oregami.data.GameDao;
import org.oregami.entities.Game;
import org.oregami.entities.GameTitle;

import com.google.inject.Inject;
import com.google.inject.persist.UnitOfWork;


@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GamesResource {

	public GameDao gameRepository;
	
	@Inject private UnitOfWork unitOfWork;
	
	@Inject
	public GamesResource(GameDao gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	  
	@GET
	@Path("/init")
	public Game init() {
		Game ret = null;
		gameRepository.getTransaction().begin();
		Game game = null;
		game = gameRepository.findOne(1L);
		if (game==null) {
			game = new org.oregami.entities.Game();
			game.setTagLineDescription("Monkey Island");
			gameRepository.save(game);
		}
		game.setTagLineDescription("Monkey Island");
		game.setTagLineDescription(game.getTagLineDescription() + " rnd" + Math.random());
		if (game.getGameTitleList().size()==0) {
			game.addGameTitle(new GameTitle("Monkey Island - Title"));
		}
		GameTitle gameTitle = game.getGameTitleList().iterator().next();
		gameTitle.setTitle("Monkey Island - Title" + " rnd" + Math.random());
		gameRepository.update(game);
		gameRepository.getTransaction().commit();
		ret = game;
		return ret;
	}
	
	@GET
	@Path("/test")
	public Game test() {
		return null;
	}
}
