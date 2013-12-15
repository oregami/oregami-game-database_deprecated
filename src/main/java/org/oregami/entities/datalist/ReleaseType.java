package org.oregami.entities.datalist;

import javax.persistence.Entity;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+2+-+RG+Release+Types
 * used in ReleaseGroup-Entity
 */
@Entity
public class ReleaseType extends BaseList {

	public ReleaseType(String value) {
		super(value);
	}
	
	public static final String NATIVE_DEVELOPMENT = "NATIVE_DEVELOPMENT";
	public static final String PORT = "PORT";
	public static final String EMULATOR_RELEASE = "EMULATOR_RELEASE";

}
