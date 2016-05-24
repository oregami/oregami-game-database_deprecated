package org.oregami.entities.datalist;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+2+-+RG+Release+Types
 * used in ReleaseGroup-Entity
 */
@Entity
@Audited
@NamedQueries({@NamedQuery(name="ReleaseType.GetAll", query = "from ReleaseType e")})
public class ReleaseType extends BaseList {

	public ReleaseType(String value) {
		super(value);
	}
	
	public ReleaseType() {
		super("");
	}
	
	public static final String NATIVE_DEVELOPMENT = "NATIVE_DEVELOPMENT";
	public static final String PORT = "PORT";
	public static final String EMULATOR_RELEASE = "EMULATOR_RELEASE";

}
