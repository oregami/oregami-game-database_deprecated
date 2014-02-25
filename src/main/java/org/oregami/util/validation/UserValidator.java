package org.oregami.util.validation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.oregami.data.UserDao;
import org.oregami.entities.user.User;
import org.oregami.service.ServiceError;
import org.oregami.service.ServiceErrorContext;
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
            errors.add(new ServiceError(new ServiceErrorContext("user.username"),"org.oregami.user.username.alreadyExists"));
        }

        return errors;

    }

    public List<ServiceError> validateRequiredFields() {
        List<ServiceError> errorMessages = new ArrayList<ServiceError>();

        if (org.apache.commons.lang.StringUtils.isEmpty(userData.getUsername())) {
            errorMessages.add(new ServiceError(new ServiceErrorContext("user.username"), "org.oregami.user.username.isEmpty"));
        }

        if (StringUtils.isEmpty(userData.getEmail())) {
        	errorMessages.add(new ServiceError(new ServiceErrorContext("user.email"), "org.oregami.user.email.isEmpty"));
        }

        if (StringUtils.isEmpty(userData.getPassword())) {
        	errorMessages.add(new ServiceError(new ServiceErrorContext("user.password"), "org.oregami.user.password.isEmpty"));        	
        } else if (StringUtils.length(userData.getPassword())<6) {
        	errorMessages.add(new ServiceError(new ServiceErrorContext("user.password"), "org.oregami.user.password.tooShort"));
        }

        return errorMessages;
    }
}
