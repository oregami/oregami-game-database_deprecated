package org.oregami.util.validation;

import org.apache.commons.lang.StringUtils;
import org.oregami.data.PublicationFranchiseDao;
import org.oregami.entities.Publication;
import org.oregami.entities.PublicationFranchise;
import org.oregami.entities.PublicationIssue;
import org.oregami.service.FieldNames;
import org.oregami.service.ServiceError;
import org.oregami.service.ServiceErrorContext;
import org.oregami.service.ServiceErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class PublicationFranchiseValidator {

    private final PublicationFranchiseDao publicationFranchiseDao;

    private final PublicationFranchise publicationFranchise;

    private final int nameMinLenght = 3;

    public PublicationFranchiseValidator(PublicationFranchiseDao publicationFranchiseDao, PublicationFranchise publicationFranchise) {
        if (publicationFranchiseDao == null) {
            throw new RuntimeException("org.oregami.publicationFranchiseValidator.NoPublicationFranchiseDaoGiven");
        }
        if (publicationFranchise == null) {
            throw new RuntimeException("org.oregami.publicationFranchiseValidator.NoPublicationFranchiseGiven");
        }

        this.publicationFranchiseDao = publicationFranchiseDao;
        this.publicationFranchise = publicationFranchise;
    }

    public List<ServiceError> validateForCreation() {
        List<ServiceError> errors = new ArrayList<ServiceError>();

        errors.addAll(validateRequiredFields());
        errors.addAll(validatePublications(publicationFranchise));

        return errors;

    }


    public List<ServiceError> validateRequiredFields() {
        List<ServiceError> errorMessages = new ArrayList<ServiceError>();

        if (StringUtils.isEmpty(publicationFranchise.getName())) {
            errorMessages.add(new ServiceError(new ServiceErrorContext(FieldNames.PUBLICATIONFRANCHISE_NAME), ServiceErrorMessage.NAME_EMPTY));
        }
        else if (StringUtils.length(publicationFranchise.getName()) < nameMinLenght) {
        	errorMessages.add(new ServiceError(new ServiceErrorContext(FieldNames.PUBLICATIONFRANCHISE_NAME), ServiceErrorMessage.NAME_TOO_SHORT));
        }


        return errorMessages;
    }

	public List<ServiceError> validateForUpdate() {
		
        List<ServiceError> errors = new ArrayList<ServiceError>();

        errors.addAll(validateRequiredFields());
        errors.addAll(validatePublications(publicationFranchise));

        return errors;
	}

    public List<ServiceError> validatePublications(PublicationFranchise publicationFranchise) {

        List<ServiceError> errorMessages = new ArrayList<ServiceError>();

        for (Publication publication : publicationFranchise.getPublicationList()) {

            if (StringUtils.isEmpty(publication.getName())) {
                errorMessages.add(
                        new ServiceError(
                                new ServiceErrorContext(
                                        FieldNames.PUBLICATION_NAME,
                                        publication.getId()
                                )
                                , ServiceErrorMessage.NAME_EMPTY
                        )
                );
            }

            for (PublicationIssue issue : publication.getPublicationIssueList()) {
                if (issue.getIssueNameYear()<=0) {
                    errorMessages.add(
                            new ServiceError(
                                    new ServiceErrorContext(
                                            FieldNames.PUBLICATIONISSUE_NAMEYEAR,
                                            issue.getId()
                                    )
                                    , ServiceErrorMessage.FIELD_EMPTY
                            )
                    );
                }
            }
            ;

        }
        ;

        return errorMessages;
    }


}
