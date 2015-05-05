package org.oregami.entities.datalist;

import javax.persistence.Entity;

@Entity
public class HardwarePlatformType extends BaseList {

	public HardwarePlatformType(String value) {
		super(value);
	}

	public HardwarePlatformType() {
		super("");
	}

	public static final String HOME_COMPUTERS_EUROPE_NORTHAMERICA = "HOME_COMPUTERS_EUROPE_NORTHAMERICA";
    public static final String HOME_COMPUTERS_ASIA = "HOME_COMPUTERS_ASIA";
    public static final String CONSOLES_EUROPE_NORTHAMERICA = "CONSOLES_EUROPE_NORTHAMERICA";
    public static final String CONSOLES_NON_EUROPE_NORTHAMERICA = "CONSOLES_NON_EUROPE_NORTHAMERICA";
    public static final String HANDHELDS_EUROPE_NORTHAMERICA = "HANDHELDS_EUROPE_NORTHAMERICA";
    public static final String HANDHELDS_NON_EUROPE_NORTHAMERICA = "HANDHELDS_NON_EUROPE_NORTHAMERICA";
    public static final String MOBILE = "MOBILE";
    public static final String NO_HARDWARE = "NO_HARDWARE";



}
