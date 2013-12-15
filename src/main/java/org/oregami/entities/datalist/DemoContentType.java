package org.oregami.entities.datalist;

import javax.persistence.Entity;

/**
 * see http://wiki.oregami.org/display/OR/Tag+List+1+-+Demo+Content
 * Used in ReleaseGroup-Entity
 */
@Entity
public class DemoContentType extends BaseList {

	private static final long serialVersionUID = 3690292173015960083L;
	
	public DemoContentType(String value) {
		super(value);
	}
	
	DemoContentType() {
		super("");
	}
	
	public static final String TECH_DEMO = "TECH_DEMO";
	public static final String TIME_LIMIT = "TIME_LIMIT";
	public static final String CONTENT_LIMIT = "CONTENT_LIMIT";
	public static final String SCORE_CAP = "SCORE_CAP";
	public static final String LEVEL_CAP = "LEVEL_CAP";
	public static final String SAVING_DISABLED = "SAVING_DISABLED";
	public static final String ABSOLUTE_PLAY_COUNT_LIMIT = "ABSOLUTE_PLAY_COUNT_LIMIT";
	public static final String ABSOLUTE_PLAY_TIME_LIMIT = "ABSOLUTE_PLAY_TIME_LIMIT";

}
