package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.ReleaseType;

import javax.persistence.EntityManager;

public class ReleaseTypeDao extends GenericDAOUUIDImpl<ReleaseType, Long>{

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
