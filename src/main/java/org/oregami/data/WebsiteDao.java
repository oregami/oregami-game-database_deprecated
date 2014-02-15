package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.Website;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class WebsiteDao extends GenericDAOUUIDImpl<Website, String>{

	@Inject
	public WebsiteDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=Website.class;
	}
	
	
}
