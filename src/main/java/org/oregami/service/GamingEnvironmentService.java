package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.GamingEnvironmentDao;
import org.oregami.data.GenericDAOUUID;
import org.oregami.entities.GamingEnvironment;
import org.oregami.util.validation.IEntityValidator;
import org.oregami.util.validation.NullValidator;

public class GamingEnvironmentService extends TopLevelEntityService<GamingEnvironment> {

	@Inject
	private GamingEnvironmentDao dao;

    @Override
    public GenericDAOUUID<GamingEnvironment, String> getDao() {
        return dao;
    }

    @Override
    public IEntityValidator buildEntityValidator(GamingEnvironment entity) {
        return new NullValidator(entity);
    }
}
