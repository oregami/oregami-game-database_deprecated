package org.oregami.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.oregami.entities.GamingEnvironment;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class GamingEnvironmentDao extends GenericDAOUUIDImpl<GamingEnvironment, String>{

	@Inject
	public GamingEnvironmentDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=GamingEnvironment.class;
	}
	
	
    @SuppressWarnings("unchecked")
	public List<GamingEnvironment> findByTitle(String title) {
        List<GamingEnvironment> geList = getEntityManager()
        		.createNativeQuery("SELECT * FROM GamingEnvironment t where title = :value ", GamingEnvironment.class).setParameter("value", title).getResultList(); 
        return geList;
    }


}
