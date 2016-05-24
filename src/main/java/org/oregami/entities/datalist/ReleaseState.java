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

	public static final String NATIVE_DEVELOPMENT = "NATIVE_DEVELOPMENT";
	public static final String PORT = "PORT";
	public static final String EMULATOR_RELEASE = "EMULATOR_RELEASE";

}
