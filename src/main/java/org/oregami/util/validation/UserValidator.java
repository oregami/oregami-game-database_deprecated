package org.oregami.util.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.oregami.data.UserDao;
import org.oregami.entities.user.User;
import org.oregami.service.FieldNames;
import org.oregami.service.ServiceError;
import org.oregami.service.ServiceErrorContext;
import org.oregami.service.ServiceErrorMessage;
import org.oregami.util.exception.OregamiRuntimeException;

public class UserValidator {

    private final UserDao userDao;

    private final User userData;

    public UserValidator(UserDao userDaoManager, User userData) {
        if (userDaoManager == null) {
            throw new OregamiRuntimeException("org.oregami.uservalidator.NoUserDaoManagerGiven");
        }
        if (userData == null) {
            throw new OregamiRuntimeException("org.oregami.uservalidator.NoUserGiven");
        }

        this.userDao = userDaoManager;
        this.userData = userData;
    }

    public List<ServiceError> validateForRegister() {
        List<ServiceError> errors = new ArrayList<ServiceError>();
        errors.addAll(validateRequiredFields());

        if (userDao.findOneByUsername(userData.getUsername())!=null) {
            errors.add(new ServiceError(new ServiceErrorContext(FieldNames.USER_USERNAME),ServiceErrorMessage.USER_USERNAME_ALREADY_EXISTS));
        }

        return errors;

    }

    public List<ServiceError> validateRequiredFields() {
        List<ServiceError> errorMessages = new ArrayList<ServiceError>();

        if (org.apache.commons.lang.StringUtils.isEmpty(userData.getUsername())) {
            errorMessages.add(new ServiceError(new ServiceErrorContext(FieldNames.USER_USERNAME), ServiceErrorMessage.USER_USERNAME_EMPTY));
        }

        if (StringUtils.isEmpty(userData.getEmail())) {
        	errorMessages.add(new ServiceError(new ServiceErrorContext(FieldNames.USER_EMAIL), ServiceErrorMessage.USER_EMAIL_EMPTY));
        }

        if (StringUtils.isEmpty(userData.getPassword())) {
        	errorMessages.add(new ServiceError(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_EMPTY));        	
        } else if (StringUtils.length(userData.getPassword())<6) {
        	errorMessages.add(new ServiceError(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_TOO_SHORT));
        }

        return errorMessages;
    }
}
