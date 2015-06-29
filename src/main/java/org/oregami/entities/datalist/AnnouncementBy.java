package org.oregami.entities.datalist;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

/**
 * see 
 * http://wiki.oregami.org/display/OR/Tag+List+3+-+Censorship+Types
 * Used in ReleaseGroup-Entity
 */
@Entity
@Audited
@NamedQueries({@NamedQuery(name="AnnouncementBy.GetAll", query = "from AnnouncementBy e")})
public class AnnouncementBy extends BaseList {

	public AnnouncementBy(String value) {
		super(value);
	}
	
	public AnnouncementBy() {
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
