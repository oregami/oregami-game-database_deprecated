package org.oregami.service;

import org.junit.Assert;
import org.junit.Test;
import org.oregami.entities.BaseEntityUUID;

public class TestServiceResult {
	
	@Test
	public void testContainsErrorMessage() {
		ServiceResult<BaseEntityUUID> result = new ServiceResult<BaseEntityUUID>();
		result.addMessage(new ServiceErrorContext(FieldNames.USER_EMAIL),ServiceErrorMessage.USER_EMAIL_EMPTY);
		
		Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_EMAIL), ServiceErrorMessage.USER_EMAIL_EMPTY)));
		Assert.assertFalse(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_EMPTY)));
	}

	
	@Test
	public void testContainsErrorMessage2() {
		ServiceResult<BaseEntityUUID> result = new ServiceResult<BaseEntityUUID>();
		result.addMessage(new ServiceErrorContext(FieldNames.USER_EMAIL), ServiceErrorMessage.USER_EMAIL_EMPTY);
		result.addMessage(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_EMPTY);
		result.addMessage(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_TOO_SHORT);
		
		Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_EMAIL), ServiceErrorMessage.USER_EMAIL_EMPTY)));
		Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_EMPTY)));
		Assert.assertTrue(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_TOO_SHORT)));
		Assert.assertFalse(result.containsError(new ServiceError(new ServiceErrorContext(FieldNames.USER_USERNAME), ServiceErrorMessage.USER_USERNAME_ALREADY_EXISTS)));
		
		
	}
	
}
