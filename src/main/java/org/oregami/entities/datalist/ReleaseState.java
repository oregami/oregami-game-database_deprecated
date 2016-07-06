package org.oregami.entities.datalist;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

/**
 * see http://wiki.oregami.org/
 * used in ReleaseGroup-Entity
 */
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name="ReleaseState.GetAll", query =
				"from ReleaseState g")
})
public class ReleaseState extends BaseList {

	public ReleaseState(String value) {
		super(value);
	}

	public ReleaseState() {
		super("");
	}

	public static final String OFFICIALLY_RELEASED_DEVELOPMENT_FINISHED = "OFFICIALLY_RELEASED_DEVELOPMENT_FINISHED";
    public static final String OFFICIALLY_RELEASED_CLOSED_BETA = "OFFICIALLY_RELEASED_CLOSED_BETA";
    public static final String OFFICIALLY_RELEASED_OPEN_BETA = "OFFICIALLY_RELEASED_OPEN_BETA";
    public static final String OFFICIALLY_RELEASED_CONTINUOUS_DEVELOPMENT = "OFFICIALLY_RELEASED_CONTINUOUS_DEVELOPMENT";
	public static final String UNOFFICIALLY_RELEASED = "UNOFFICIALLY_RELEASED";

}
