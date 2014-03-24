package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.Region;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class RegionDao extends GenericDAOImpl<Region, Long>{

	@Inject
	public RegionDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=Region.class;
	}
	
	
	public Region findByExactName(String name) {
    	Region r = (Region) getEntityManager()
        		.createNativeQuery("SELECT * FROM Region r where lower(r.name) = :value ", Region.class).setParameter("value", name.toLowerCase()).getSingleResult(); 
        return r;
    }


}
