package org.oregami.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;

@Entity
@TopLevelEntity(discriminator = TopLevelEntity.Discriminator.REGION)
@NamedQueries({@NamedQuery(name="Region.GetAll", query = "from Region r")})
@Audited
public class Region extends BaseEntityUUID {

	private static final long serialVersionUID = -6689057258957874499L;

	private String name;
	private boolean isCountry;
	private boolean isBusinessRegion;
	private String description;
	
	public Region(String name) {
		this.setName(name);
	}
	
	public Region(String name, boolean isCountry, boolean isBusinessRegion, String description) {
		this.name = name;
		this.isCountry = isCountry;
		this.isBusinessRegion = isBusinessRegion;
		this.description = description;
	}
	
	Region() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCountry() {
		return isCountry;
	}

	public void setCountry(boolean isCountry) {
		this.isCountry = isCountry;
	}

	public boolean isBusinessRegion() {
		return isBusinessRegion;
	}

	public void setBusinessRegion(boolean isBusinessRegion) {
		this.isBusinessRegion = isBusinessRegion;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static final String GERMANY = "GERMANY";
	public static final String UNITED_STATES = "UNITED_STATES";
	public static final String UNITED_KINGDOM = "UNITED_KINGDOM";
	public static final String FRANCE = "FRANCE";
	public static final String JAPAN = "JAPAN";
	public static final String CHINA = "CHINA";

	public static final String EUROPE = "EUROPE";
	public static final String NORTH_AMERICA = "NORTH_AMERICA";

	

}
