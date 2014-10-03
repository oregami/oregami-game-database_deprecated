package org.oregami.service;

import com.google.inject.Inject;
import org.oregami.data.PublicationFranchiseDao;
import org.oregami.entities.PublicationFranchise;
import org.oregami.util.validation.PublicationFranchiseValidator;

import java.util.List;

public class PublicationFranchiseService {

	@Inject
	private PublicationFranchiseDao publicationFranchiseDao;
	
    public ServiceResult<PublicationFranchise> createNewPublicationFranchise(PublicationFranchise publicationFranchiseData) {
        PublicationFranchiseValidator validator = buildPublicationFranchiseValidator(publicationFranchiseData);

        List<ServiceError> errorMessages = validator.validateForCreation();

        PublicationFranchise publicationFranchise = null;

        if (errorMessages.size() == 0) {
            publicationFranchise = publicationFranchiseData;
            publicationFranchiseDao.save(publicationFranchise);
        }

        return new ServiceResult<PublicationFranchise>(publicationFranchiseData, errorMessages);
    }
    
    public ServiceResult<PublicationFranchise> updatePublicationFranchise(PublicationFranchise publicationFranchiseData) {
        PublicationFranchiseValidator validator = buildPublicationFranchiseValidator(publicationFranchiseData);

        List<ServiceError> errorMessages = validator.validateForUpdate();

        PublicationFranchise publicationFranchise = null;

        if (errorMessages.size() == 0) {
            publicationFranchise = publicationFranchiseData;
            publicationFranchiseDao.update(publicationFranchise);
        }

        return new ServiceResult<PublicationFranchise>(publicationFranchiseData, errorMessages);
    }    

	private PublicationFranchiseValidator buildPublicationFranchiseValidator(PublicationFranchise newPublicationFranchise) {
		return new PublicationFranchiseValidator(this.publicationFranchiseDao, newPublicationFranchise);
	}
}
