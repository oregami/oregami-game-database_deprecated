package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.TitleLocation;

import javax.persistence.EntityManager;

public class TitleLocationDao extends BaseListDao<TitleLocation>{

	@Inject
	public TitleLocationDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=TitleLocation.class;
	}

}
