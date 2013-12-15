package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.datalist.DemoContentType;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class DemoContentTypeDao extends GenericDAOImpl<DemoContentType, Long>{

	@Inject
	public DemoContentTypeDao(Provider<EntityManager> emf) {
		super(emf);
	}

    public DemoContentType findByName(String value) {
    	DemoContentType type = (DemoContentType)getEntityManager()
                .createQuery("SELECT t FROM DemoContentType t where t.value = '" + value + "'").getSingleResult(); 
        return type;
    }
	
}
