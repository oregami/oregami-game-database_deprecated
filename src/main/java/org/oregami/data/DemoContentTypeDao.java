package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.datalist.DemoContentType;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DemoContentTypeDao extends BaseListDao<DemoContentType>{

	@Inject
	public DemoContentTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=DemoContentType.class;
	}

}
