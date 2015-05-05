package org.oregami.util.validation;

import org.apache.commons.lang.StringUtils;
import org.oregami.entities.TransliteratedString;
import org.oregami.entities.datalist.Script;
import org.oregami.service.FieldNames;
import org.oregami.service.ServiceError;
import org.oregami.service.ServiceErrorContext;
import org.oregami.service.ServiceErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class ScriptValidator implements IEntityValidator {

    private final Script entity;

    private final int nameMinLenght = 3;

    public ScriptValidator(Script entity) {
        if (entity == null) {
            throw new RuntimeException("org.oregami.ScriptValidator.NoEntityGiven");
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
        if (StringUtils.isEmpty(entity.getValue())) {
            errorMessages.add(new ServiceError(new ServiceErrorContext(FieldNames.ANY_FIELD, id), ServiceErrorMessage.FIELD_EMPTY));
        }
        else if (StringUtils.length(entity.getValue()) < nameMinLenght) {
            errorMessages.add(new ServiceError(new ServiceErrorContext(FieldNames.ANY_FIELD, id), ServiceErrorMessage.FIELD_TOO_SHORT));
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
