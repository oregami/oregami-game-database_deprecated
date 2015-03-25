package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.GameDao;
import org.oregami.data.GameTitleDao;
import org.oregami.data.GenericDAOUUID;
import org.oregami.entities.Game;
import org.oregami.entities.GameTitle;
import org.oregami.util.validation.GameTitleValidator;
import org.oregami.util.validation.GameValidator;
import org.oregami.util.validation.IEntityValidator;

public class GameTitleService extends TopLevelEntityService<GameTitle> {

	@Inject
	private GameTitleDao dao;
	
    @Override
    public GenericDAOUUID<GameTitle, String> getDao() {
        return dao;
    }

    @Override
    public IEntityValidator buildEntityValidator(GameTitle entity) {
        return new GameTitleValidator(entity);
    }
}
