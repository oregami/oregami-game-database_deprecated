package org.oregami.service;

import org.oregami.data.GameDao;
import org.oregami.data.GenericDAOUUID;
import org.oregami.entities.Game;
import org.oregami.util.validation.GameValidator;
import org.oregami.util.validation.IEntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService extends TopLevelEntityService<Game> {

	@Autowired
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
