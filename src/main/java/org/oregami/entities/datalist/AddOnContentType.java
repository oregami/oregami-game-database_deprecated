package org.oregami.entities.datalist;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Entity
@Audited
@NamedQueries({@NamedQuery(name="AddOnContentType.GetAll", query = "from AddOnContentType e")})
public class AddOnContentType extends BaseList {

	public AddOnContentType(String value) {
		super(value);
	}

	public AddOnContentType() {
		super("");
	}

    public static final String NEW_CAMPAIGNS = "NEW_CAMPAIGNS";
	public static final String ADDITIONAL_LEVELS_OR_MISSIONS = "ADDITIONAL_LEVELS_OR_MISSIONS";
	public static final String EXTENDED_AREA_OF_GAMEPLAY = "EXTENDED_AREA_OF_GAMEPLAY";
	public static final String NEW_PLAYABLE_CHARACTERS = "NEW_PLAYABLE_CHARACTERS";
	public static final String CHANGES_TO_GAMERULES_OR_GAMEMECHANICS = "CHANGES_TO_GAMERULES_OR_GAMEMECHANICS";
	public static final String NEW_FEATURES = "NEW_FEATURES";
	public static final String NEW_ITEMS = "NEW_ITEMS";
	public static final String NEW_ENEMIES = "NEW_ENEMIES";


}
