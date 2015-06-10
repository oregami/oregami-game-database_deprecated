package org.oregami.entities.datalist;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Entity
@Audited
@NamedQueries({@NamedQuery(name="SoftwarePlatformType.GetAll", query = "from SoftwarePlatformType spt")})
public class SoftwarePlatformType extends BaseList {

	public SoftwarePlatformType(String value) {
		super(value);
	}

	public SoftwarePlatformType() {
		super("");
	}

    public static final String DESKTOP = "DESKTOP";
	public static final String MOBILE = "MOBILE";
	public static final String SPECIAL_SOFTWARE = "SPECIAL_SOFTWARE";
	public static final String BUILT_IN = "BUILT_IN";



}
