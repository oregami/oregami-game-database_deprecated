package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.AddOnContentType;

import javax.persistence.EntityManager;

public class AddOnContentTypeDao extends BaseListDao<AddOnContentType>{

	@Inject
	public AddOnContentTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=AddOnContentType.class;
	}

}
