package org.oregami.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.oregami.entities.Game;

import com.google.inject.Inject;

public class GameDao extends GenericDAOImpl<Game, Long>{

	@Inject
	public GameDao(EntityManager man) {
		super(man);
		entityClass=Game.class;
	}
	
	
    @SuppressWarnings("unchecked")
	public List<Game> findByName(String name) {
        List<Game> games = (List<Game>)getEntityManager()
        		.createNativeQuery("SELECT * FROM Game g, GameTitle t where g.id=t.GameId and lower(t.title) like '%" + name.toLowerCase() + "%'", Game.class).getResultList(); 
        return games;
    }


}
