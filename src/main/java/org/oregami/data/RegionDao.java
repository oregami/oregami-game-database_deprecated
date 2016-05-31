package org.oregami.data;

import org.oregami.entities.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RegionDao extends GenericDAOUUIDImpl<Region, String> {

	@Autowired
	public RegionDao(EntityManager em) {
		super(em);
		entityClass=Region.class;
	}
	
	
	public Region findByExactName(String name) {
    	Region r = (Region) getEntityManager()
        		.createNativeQuery("SELECT * FROM Region r where lower(r.name) = :value ", Region.class).setParameter("value", name.toLowerCase()).getSingleResult();
        return r;
    }


}
