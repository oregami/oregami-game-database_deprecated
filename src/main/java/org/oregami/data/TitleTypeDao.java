package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.datalist.TitleType;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class TitleTypeDao extends BaseListDao<TitleType>{

	@Inject
	public TitleTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=TitleType.class;
	}
	

}
