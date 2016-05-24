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
@NamedQueries({@NamedQuery(name="CensorshipType.GetAll", query = "from CensorshipType e")})
public class CensorshipType extends BaseList {

	public CensorshipType(String value) {
		super(value);
	}
	
	public CensorshipType() {
		super("");
	}

	public static final String VISUAL_CENSORSHIP_GORE = "VISUAL_CENSORSHIP_GORE";
	public static final String VISUAL_CENSORSHIP_NUDISM = "VISUAL_CENSORSHIP_NUDISM";
	public static final String VISUAL_CENSORSHIP_OTHER = "VISUAL_CENSORSHIP_OTHER";
	public static final String AUDIO_CENSORSHIP = "AUDIO_CENSORSHIP";
	public static final String SCRIPT_CENSORSHIP = "SCRIPT_CENSORSHIP";
	public static final String GAMEPLAY_CENSORSHIP_VIOLENCE = "GAMEPLAY_CENSORSHIP_VIOLENCE";
	public static final String GAMEPLAY_CENSORSHIP_OTHER = "GAMEPLAY_CENSORSHIP_OTHER";

}
