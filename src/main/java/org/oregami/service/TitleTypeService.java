package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.GameDao;
import org.oregami.data.GenericDAOUUID;
import org.oregami.data.TitleTypeDao;
import org.oregami.entities.Game;
import org.oregami.entities.datalist.TitleType;
import org.oregami.util.validation.GameValidator;
import org.oregami.util.validation.IEntityValidator;
import org.oregami.util.validation.NullValidator;

public class TitleTypeService extends TopLevelEntityService<TitleType> {

	@Inject
	private TitleTypeDao dao;

    @Override
    public GenericDAOUUID<TitleType, String> getDao() {
        return dao;
    }

    @Override
    public IEntityValidator buildEntityValidator(TitleType entity) {
        return new NullValidator(entity);
    }
}
