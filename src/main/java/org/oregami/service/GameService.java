package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.GameDao;
import org.oregami.data.GenericDAOUUID;
import org.oregami.data.PublicationFranchiseDao;
import org.oregami.entities.Game;
import org.oregami.entities.PublicationFranchise;
import org.oregami.util.validation.GameValidator;
import org.oregami.util.validation.IEntityValidator;
import org.oregami.util.validation.PublicationFranchiseValidator;

public class GameService extends TopLevelEntityService<Game> {

	@Inject
	private GameDao dao;
	
    @Override
    public GenericDAOUUID<Game, String> getDao() {
        return dao;
    }

    @Override
    public IEntityValidator buildEntityValidator(Game entity) {
        return new GameValidator(entity);
    }
}
