package org.oregami.entities.datalist;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+1+-+Release+Group+Reasons
 */
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name="ReleaseGroupReason.GetAll", query =
				"from ReleaseGroupReason r")
})
public class ReleaseGroupReason extends BaseList {

	private static final long serialVersionUID = -7611326913084521870L;

	public ReleaseGroupReason(String value) {
		super(value);
	}

	public ReleaseGroupReason() {
		super("");
	}

	public static final String ORIGINAL = "ORIGINAL";
	public static final String DEMO_PROMO = "DEMO_PROMO";
	public static final String ENHANCED = "ENHANCED";
	public static final String REMAKE = "REMAKE";
	public static final String CENSORED = "CENSORED";

}
