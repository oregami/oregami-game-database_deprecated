package org.oregami.entities.datalist;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+27+-+Release+Business+Models
 */
@Entity
@Audited
@NamedQueries({@NamedQuery(name="BusinessModel.GetAll", query = "from BusinessModel e")})
public class BusinessModel extends BaseList {

	private static final long serialVersionUID = 8648455079483420964L;
	
	public BusinessModel(String value) {
		super(value);
	}
	
	public BusinessModel() {
		super("");
	}
	
	public static final int COMMERCIAL_FULL_PRICE = 1;
	public static final int COMMERCIAL_BUDGET_VALUE = 2;
	public static final int BUDGET_RE_RELEASE_FULL_PRICE = 3;
	public static final int BUDGET_RE_RELEASE_BUDGET_VALUE = 4;
	public static final int OPEN_SOURCE = 5;
	public static final int FREEWARE = 6;
	public static final int SHAREWARE = 7;
	public static final int PIRATE_RELEASE = 8;

}
