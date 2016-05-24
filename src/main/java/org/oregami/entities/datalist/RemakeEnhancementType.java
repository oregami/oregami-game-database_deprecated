package org.oregami.entities.datalist;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * see 
 * http://wiki.oregami.org/display/OR/Tag+List+2+-+Remake+Enhancements
 * Used in ReleaseGroup-Entity
 */
@Entity
@Audited
@NamedQueries({@NamedQuery(name="RemakeEnhancementType.GetAll", query = "from RemakeEnhancementType e")})
public class RemakeEnhancementType extends BaseList {

	public RemakeEnhancementType(String value) {
		super(value);
	}
	
	public RemakeEnhancementType() {
		super("");
	}
	
	public static final String ENHANCED_GRAPHICS = "ENHANCED_GRAPHICS";
	public static final String ENHANCED_SOUND = "ENHANCED_SOUND";
	public static final String ADDITIONAL_FMV = "ADDITIONAL_FMV";
	public static final String ADDITIONAL_SPEECH = "ADDITIONAL_SPEECH";
	public static final String IMPROVED_UI = "IMPROVED_UI";
	public static final String ADDITIONAL_CONTENT = "ADDITIONAL_CONTENT";
	public static final String BUGFREE_RELEASE = "BUGFREE_RELEASE";

}
