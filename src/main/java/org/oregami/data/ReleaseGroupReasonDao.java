package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.datalist.ReleaseGroupReason;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ReleaseGroupReasonDao extends GenericDAOImpl<ReleaseGroupReason, Long>{

	@Inject
	public ReleaseGroupReasonDao(Provider<EntityManager> emf) {
		super(emf);
	}

    public ReleaseGroupReason findByName(String value) {
    	ReleaseGroupReason type = (ReleaseGroupReason)getEntityManager()
                .createQuery("SELECT t FROM ReleaseGroupReason t where t.value = '" + value + "'").getSingleResult(); 
        return type;
    }
	
}
