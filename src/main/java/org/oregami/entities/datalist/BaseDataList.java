package org.oregami.entities.datalist;

import javax.persistence.MappedSuperclass;

import org.oregami.entities.BaseEntity;

@MappedSuperclass
public abstract class BaseDataList extends BaseEntity {

	protected static final long serialVersionUID = 1L;

	public BaseDataList(String value) {
		super();
		this.value = value;
	}
	
	private String value = null;

	public String getValue() {
		return value;
	}

	
}
