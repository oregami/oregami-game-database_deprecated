package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.TitleType;

import javax.persistence.EntityManager;

public class TitleTypeDao extends BaseListDao<TitleType>{

	@Inject
	public TitleTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=TitleType.class;
	}
	

}
