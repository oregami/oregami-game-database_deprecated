package org.oregami.entities.datalist;

import org.oregami.entities.BaseEntityUUID;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseList extends BaseEntityUUID {

	private static final long serialVersionUID = 8492563207129737767L;

	public BaseList(String value) {
		super();
		this.value = value;
	}

	private String value = null;

	public String getValue() {
		return value;
	}


}
