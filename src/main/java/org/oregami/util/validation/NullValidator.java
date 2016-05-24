package org.oregami.util.validation;

import org.oregami.entities.BaseEntityUUID;
import org.oregami.service.ServiceError;

import java.util.ArrayList;
import java.util.List;

public class NullValidator implements IEntityValidator {

    private final BaseEntityUUID entity;

    public NullValidator(BaseEntityUUID e) {
        if (e == null) {
            throw new RuntimeException("org.oregami.NullValidator.NoEntityGiven");
        }
        this.entity = e;
    }

    @Override
    public List<ServiceError> validateForCreation() {
        List<ServiceError> errors = new ArrayList<ServiceError>();

        errors.addAll(validateRequiredFields());

        return errors;

    }

    @Override
    public List<ServiceError> validateRequiredFields() {
        List<ServiceError> errorMessages = new ArrayList<ServiceError>();

        String id = entity.getId();
        if (id==null) {
            id = entity.getValidationId();
        }

        return errorMessages;
    }

    @Override
	public List<ServiceError> validateForUpdate() {
        List<ServiceError> errors = new ArrayList<ServiceError>();
        errors.addAll(validateRequiredFields());
        return errors;
	}


}
