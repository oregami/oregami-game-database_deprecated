package org.oregami.entities.datalist;

import javax.persistence.Entity;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * see http://wiki.oregami.org/display/OR/Data+List+5+-+Game+Entry+Type
 * Used in Game-Entity
 */
@Entity
@NamedQueries({
	@NamedQuery(name="GameEntryType.GetAll", query = 
			"from GameEntryType g")
})
public class GameEntryType extends BaseList {

	private static final long serialVersionUID = -2058077337831511047L;
	
	public GameEntryType(String value) {
		super(value);
	}
	
	GameEntryType() {
		super("");
	}
	
	public static final String GAME = "GAME";
	public static final String EPISODIC_GAME = "EPISODIC_GAME";
	public static final String COMPILATION = "COMPILATION";
	public static final String ADD_ON_SIGNIFICANT = "ADD_ON_SIGNIFICANT";
	public static final String ADD_ON_NOT_SIGNIFICANT = "ADD_ON_NOT_SIGNIFICANT";
	public static final String EPISODE = "EPISODE";

}
