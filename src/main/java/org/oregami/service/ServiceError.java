package org.oregami.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents errors that occur during service layer calls. 
 * The messageName is a internal string used for translations.
 * The context defines in which field the error occurred (e.g. the web form field name).
 * 
 * @author twendelmuth
 * 
 */
public class ServiceError {

    private String messageName;
    
    private ServiceErrorContext context;

    public ServiceError(ServiceErrorContext context, String messageName) {
    	this.context = context;
        this.messageName = messageName;
    }
    
    public ServiceError(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public static List<ServiceError> buildServiceErrors(String... errors) {
        List<ServiceError> serviceErrors = new ArrayList<ServiceError>();

        for (String error : errors) {
            serviceErrors.add(new ServiceError(error));
        }

        return serviceErrors;
    }

	public ServiceErrorContext getContext() {
		return context;
	}

	public void setContext(ServiceErrorContext context) {
		this.context = context;
	}
	
	@Override
	public String toString() {
		return context + ":" + messageName;
	}

}
