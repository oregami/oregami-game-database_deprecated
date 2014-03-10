package org.oregami.service;


import org.junit.Assert;
import org.junit.Test;

public class TestServiceError {
	
	@Test
	public void testHashCodeEqual() {
		ServiceError c1 = new ServiceError(new ServiceErrorContext("field"), ServiceErrorMessage.USER_EMAIL_EMPTY);
		ServiceError c2 = new ServiceError(new ServiceErrorContext("field"), ServiceErrorMessage.USER_EMAIL_EMPTY);
		
		Assert.assertTrue(c1.hashCode()==c2.hashCode());
		
	}
	
	@Test
	public void testHashCodeNotEqual() {
		ServiceError c1 = new ServiceError(new ServiceErrorContext(FieldNames.USER_EMAIL), ServiceErrorMessage.USER_EMAIL_EMPTY);
		ServiceError c2 = new ServiceError(new ServiceErrorContext(FieldNames.USER_PASSWORD), ServiceErrorMessage.USER_PASSWORD_EMPTY);
		ServiceError c3 = new ServiceError(new ServiceErrorContext(FieldNames.USER_USERNAME), ServiceErrorMessage.USER_USERNAME_ALREADY_EXISTS);
		
		Assert.assertFalse(c1.hashCode()==c2.hashCode());
		Assert.assertFalse(c2.hashCode()==c3.hashCode());
		Assert.assertFalse(c1.hashCode()==c3.hashCode());
		
	}
	
	@Test
	public void testEquals() {
		ServiceError c1 = new ServiceError(new ServiceErrorContext(FieldNames.USER_EMAIL), ServiceErrorMessage.USER_EMAIL_EMPTY);
		ServiceError c2 = new ServiceError(new ServiceErrorContext(FieldNames.USER_EMAIL), ServiceErrorMessage.USER_EMAIL_EMPTY);
		
		Assert.assertEquals(c1, c1);
		Assert.assertEquals(c1, c2);
		
	}	
	
	@Test
	public void testNotEquals() {
		ServiceError c1 = new ServiceError(new ServiceErrorContext("field"), ServiceErrorMessage.USER_EMAIL_EMPTY);
		ServiceError c2 = new ServiceError(new ServiceErrorContext("other"), ServiceErrorMessage.USER_EMAIL_EMPTY);
		ServiceError c3 = new ServiceError(new ServiceErrorContext("field"), ServiceErrorMessage.USER_PASSWORD_EMPTY);
		
		Assert.assertNotEquals(c1, c2);
		Assert.assertNotEquals(c1, c3);
		Assert.assertNotEquals(c2, c3);
		
	}	
	
	

	
}
