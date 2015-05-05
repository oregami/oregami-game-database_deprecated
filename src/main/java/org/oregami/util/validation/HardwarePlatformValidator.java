package org.oregami.util.validation;

import org.oregami.entities.HardwarePlatform;
import org.oregami.service.ServiceError;

import java.util.ArrayList;
import java.util.List;

public class HardwarePlatformValidator implements IEntityValidator {

    private final HardwarePlatform entity;

    private final int nameMinLenght = 3;

    public HardwarePlatformValidator(HardwarePlatform entity) {
        if (entity == null) {
            throw new RuntimeException("org.oregami.HardwarePlatformValidator.NoEntityGiven");
        }
        this.entity = entity;
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
