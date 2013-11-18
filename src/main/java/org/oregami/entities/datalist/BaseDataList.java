package org.oregami.entities.datalist;

import javax.persistence.MappedSuperclass;

import org.oregami.entities.BaseEntity;

@MappedSuperclass
public abstract class BaseDataList extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String value = null;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}
