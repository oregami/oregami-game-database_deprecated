package org.oregami.service;

import org.junit.Assert;
import org.junit.Test;
import org.oregami.entities.BaseEntity;

public class TestServiceResult {
	
	@Test
	public void testContainsErrorMessage() {
		ServiceResult<BaseEntity> result = new ServiceResult<BaseEntity>();
		result.addMessage(new ServiceErrorContext(FieldNames.USER_EMAIL),ServiceErrorMessage.USER_EMAIL_EMPTY);
		
		Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_EMAIL), ServiceErrorMessage.USER_EMAIL_EMPTY)));
		Assert.assertFalse(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_EMPTY)));
	}

	
	@Test
	public void testContainsErrorMessage2() {
		ServiceResult<BaseEntity> result = new ServiceResult<BaseEntity>();
		result.addMessage(new ServiceErrorContext(FieldNames.USER_EMAIL), ServiceErrorMessage.USER_EMAIL_EMPTY);
		result.addMessage(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_EMPTY);
		result.addMessage(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_TOO_SHORT);
		
		Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_EMAIL), ServiceErrorMessage.USER_EMAIL_EMPTY)));
		Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_EMPTY)));
		Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_TOO_SHORT)));
		Assert.assertFalse(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_USERNAME), ServiceErrorMessage.USER_USERNAME_ALREADY_EXISTS)));
		
		
	}
	
}
