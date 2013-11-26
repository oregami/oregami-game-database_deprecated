package org.oregami.entities.datalist;

import javax.persistence.Entity;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+1+-+Release+Group+Reasons
 */
@Entity
public class ReleaseGroupReason extends BaseDataList {

	public ReleaseGroupReason(String value) {
		super(value);
	}
	
	public static final String ORIGINAL = "ORIGINAL";
	public static final String DEMO_PROMO = "DEMO_PROMO";
	public static final String ENHANCED = "ENHANCED";
	public static final String REMAKE = "REMAKE";
	public static final String CENSORED = "CENSORED";

}
