package org.oregami.entities.datalist;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Entity
@Audited
@NamedQueries({@NamedQuery(name="TitleLocation.GetAll", query = "from TitleLocation e")})
public class TitleLocation extends BaseList {

	public TitleLocation(String value) {
		super(value);
	}

	public TitleLocation() {
		super("");
	}

    public static final String FRONT_COVER = "FRONT_COVER";
	public static final String BACK_COVER = "BACK_COVER";
	public static final String TITLE_SCREEN = "TITLE_SCREEN";
	public static final String LOADING_SCREEN = "LOADING_SCREEN";
	public static final String MANUAL = "MANUAL";
	public static final String MEDIUM = "MEDIUM";
	public static final String INSTALLER = "INSTALLER";
	public static final String SPINE_COVER = "SPINE_COVER";
	public static final String DESKTOP_ICON = "DESKTOP_ICON";
	public static final String START_MENU = "START_MENU";


}
