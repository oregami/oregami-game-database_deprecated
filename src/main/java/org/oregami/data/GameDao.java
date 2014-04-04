package org.oregami.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.oregami.entities.Game;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class GameDao extends GenericDAOUUIDImpl<Game, String>{

	@Inject
	public GameDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=Game.class;
	}
	
	
    @SuppressWarnings("unchecked")
	public List<Game> findByName(String name) {
        List<Game> games = getEntityManager()
        		.createNativeQuery("SELECT * FROM Game g, GameTitle t where g.id=t.GameId and lower(t.title) like '%" + name.toLowerCase() + "%'", Game.class).getResultList(); 
        return games;
    }


}
