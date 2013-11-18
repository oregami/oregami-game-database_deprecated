package org.oregami.entities.datalist;

import javax.persistence.Entity;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+5+-+Game+Entry+Type
 * Used in Game-Entity
 */
@Entity
public class GameEntryType extends BaseDataList {

	public static final String GAME = "GAME";
	public static final String EPISODIC_GAME = "EPISODIC_GAME";
	public static final String COMPILATION = "COMPILATION";
	public static final String ADD_ON_SIGNIFICANT = "ADD_ON_SIGNIFICANT";
	public static final String ADD_ON_NOT_SIGNIFICANT = "ADD_ON_NOT_SIGNIFICANT";
	public static final String EPISODE = "EPISODE";

}
