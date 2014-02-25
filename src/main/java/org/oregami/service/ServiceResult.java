package org.oregami.service;

import java.util.ArrayList;
import java.util.List;

import org.oregami.entities.BaseEntity;

/**
 * 
 * @author twendelmuth
 * 
 * @param <T>
 */
public class ServiceResult<T extends BaseEntity> {

    private T result;

    private List<ServiceError> errors;

    public ServiceResult() {
        this(null);
    }

    public ServiceResult(T result) {
        this(result, new ArrayList<ServiceError>());
    }

    public ServiceResult(T result, List<ServiceError> errors) {
        this.result = result;
        this.errors = errors;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<ServiceError> getErrors() {
        return errors;
    }

    public void setErrors(List<ServiceError> errors) {
        this.errors = errors;
    }

    public boolean wasSuccessful() {
        return errors != null && errors.size() == 0;
    }

    public boolean hasErrors() {
        return !wasSuccessful();
    }

    public void addMessage(String error) {
        errors.add(new ServiceError(error));
    }

}
