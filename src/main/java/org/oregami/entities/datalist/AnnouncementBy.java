package org.oregami.entities.datalist;

import javax.persistence.Entity;

/**
 * see 
 * http://wiki.oregami.org/display/OR/Tag+List+3+-+Censorship+Types
 * Used in ReleaseGroup-Entity
 */
@Entity
public class AnnouncementBy extends BaseList {

	private static final long serialVersionUID = 7024627464416350356L;
	
	public AnnouncementBy(String value) {
		super(value);
	}
	
	AnnouncementBy() {
		super("");
	}
	
	public static final String PRESS_RELEASE = "PRESS_RELEASE";
	public static final String PRINT_OR_ONLINE_ADVERTISMENT = "PRINT_OR_ONLINE_ADVERTISMENT";
	public static final String EDITORIAL_CONTENT = "EDITORIAL_CONTENT";
	public static final String DEVELOPER_STATEMENT = "DEVELOPER_STATEMENT";
	public static final String PROMO_WEBSITE = "PROMO_WEBSITE";
	public static final String RELEASED_SCREEN_SHOTS = "RELEASED_SCREEN_SHOTS";
	public static final String GAMEPLAY_CENSORSHIP_OTHER = "GAMEPLAY_CENSORSHIP_OTHER";

}
