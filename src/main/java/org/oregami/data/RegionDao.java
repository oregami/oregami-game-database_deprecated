package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.Region;

import javax.persistence.EntityManager;

public class RegionDao extends GenericDAOUUIDImpl<Region, String>{

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
