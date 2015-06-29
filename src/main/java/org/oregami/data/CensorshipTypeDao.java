package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.CensorshipType;

import javax.persistence.EntityManager;

public class CensorshipTypeDao extends BaseListDao<CensorshipType>{

	@Inject
	public CensorshipTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=CensorshipType.class;
	}

}
