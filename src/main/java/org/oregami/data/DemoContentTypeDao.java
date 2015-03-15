package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.DemoContentType;

import javax.persistence.EntityManager;

public class DemoContentTypeDao extends BaseListDao<DemoContentType>{

	@Inject
	public DemoContentTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=DemoContentType.class;
	}

}
