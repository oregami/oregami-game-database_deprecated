package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.GenericDAOUUID;
import org.oregami.data.PublicationFranchiseDao;
import org.oregami.entities.PublicationFranchise;
import org.oregami.util.validation.IEntityValidator;
import org.oregami.util.validation.PublicationFranchiseValidator;

public class PublicationFranchiseService extends TopLevelEntityService<PublicationFranchise> {

	@Inject
	private PublicationFranchiseDao dao;

    @Override
    public GenericDAOUUID<PublicationFranchise, String> getDao() {
        return dao;
    }

    @Override
    public IEntityValidator buildEntityValidator(PublicationFranchise entity) {
        return new PublicationFranchiseValidator(entity);
    }
}
