package org.oregami.service;

/**
 * Context af this error.
 * e.g. the field name of the web form
 */
public class ServiceErrorContext {

	/**
	 * Field name in web form e.g. "user.username"
	 */
	private String field;

	public ServiceErrorContext(String field) {
		this.field = field;
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	
	@Override
	public String toString() {
		return field;
	}
}
