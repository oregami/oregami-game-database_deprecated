package org.oregami.entities.datalist;

import javax.persistence.Entity;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * see http://wiki.oregami.org/display/OR/Tag+List+7+-+Title+Types
 * used GameToGameTitleConnection
 */
@Entity
@NamedQueries({@NamedQuery(name="TitleType.GetAll", query = "from TitleType tt")})
public class TitleType extends BaseList {

	private static final long serialVersionUID = 2000663514124552181L;
	
	public TitleType(String value) {
		super(value);
	}
	
	TitleType() {
		super("");
	}
	
	public static final String ORIGINAL_TITLE = "ORIGINAL_TITLE";
	public static final String ABBREVIATION = "ABBREVIATION";
	public static final String DEVELOPMENT_TITLE = "DEVELOPMENT_TITLE";
	public static final String INOFFICIAL_TITLE = "INOFFICIAL_TITLE";
	public static final String RE_RELEASE_TITLE = "RE_RELEASE_TITLE";
	public static final String BUDGET_RELEASE_TITLE = "BUDGET_RELEASE_TITLE";

}
