package org.oregami.service;

import org.oregami.data.GamingEnvironmentDao;
import org.oregami.data.GenericDAOUUID;
import org.oregami.domain.model.gamingEnvironment.GamingEnvironment;
import org.oregami.util.validation.IEntityValidator;
import org.oregami.util.validation.NullValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GamingEnvironmentService extends TopLevelEntityService<GamingEnvironment> {

	@Autowired
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
