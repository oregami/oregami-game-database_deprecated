package org.oregami.entities.datalist;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+29+-+Scripts
 * Used for scripted texts
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Script.GetAll", query =
			"from Script g")
})
public class Script extends BaseList {

	private static final long serialVersionUID = -3264726597931716725L;

	public Script(String value) {
		super(value);
	}

	public Script() {
		super("");
	}

	public static final String LATIN = "LATIN";
	public static final String ARABIC = "ARABIC";
	public static final String CYRILLIC = "CYRILLIC";
	public static final String JAPANESE = "JAPANESE";
	public static final String CHINESE = "CHINESE";
	public static final String KOREAN = "KOREAN";
	public static final String GREEK = "GREEK";
	public static final String HEBREW = "HEBREW";

}
