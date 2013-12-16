package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.datalist.ReleaseType;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ReleaseTypeDao extends GenericDAOImpl<ReleaseType, Long>{

	@Inject
	public ReleaseTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=ReleaseType.class;
	}
	
    public ReleaseType findByName(String value) {
    	ReleaseType type = (ReleaseType)getEntityManager()
                .createQuery("SELECT t FROM ReleaseType t where t.value = '" + value + "'").getSingleResult(); 
        return type;
    }
	
	

}
