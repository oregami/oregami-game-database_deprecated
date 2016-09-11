package org.oregami.service;

import org.oregami.data.GenericDAOUUID;
import org.oregami.data.PublicationFranchiseDao;
import org.oregami.domain.model.publicationFranchise.PublicationFranchise;
import org.oregami.util.validation.IEntityValidator;
import org.oregami.util.validation.PublicationFranchiseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationFranchiseService extends TopLevelEntityService<PublicationFranchise> {

	@Autowired
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
