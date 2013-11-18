package org.oregami.entities.datalist;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+2+-+RG+Release+Types
 */
public interface ReleaseState {

	public static final int NATIVE_DEVELOPMENT = 1;
	public static final int PORT = 2;
	public static final int EMULATOR_RELEASE = 3;

}
