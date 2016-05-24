package org.oregami.entities.datalist;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+4+-+Un-release+States
 * used in ReleaseGroup-Entity
 */
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name="UnReleasedState.GetAll", query =
				"from UnReleasedState g")
})
public class UnReleasedState extends BaseList {

	public UnReleasedState(String value) {
		super(value);
	}

	public UnReleasedState() {
		super("");
	}
	
	public static final String IN_DEVELOPMENT = "IN_DEVELOPMENT";
	public static final String DEVELOPMENT_CANCELLED = "DEVELOPMENT_CANCELLED";
	public static final String VAPORWARE = "VAPORWARE";

}
