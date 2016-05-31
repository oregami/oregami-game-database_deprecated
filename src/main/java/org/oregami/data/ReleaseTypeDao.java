package org.oregami.data;

import org.oregami.entities.datalist.ReleaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ReleaseTypeDao extends GenericDAOUUIDImpl<ReleaseType, Long> {

	@Autowired
	public ReleaseTypeDao(EntityManager em) {
		super(em);
		entityClass=ReleaseType.class;
	}

    public ReleaseType findByName(String value) {
    	ReleaseType type = (ReleaseType)getEntityManager()
                .createQuery("SELECT t FROM ReleaseType t where t.value = '" + value + "'").getSingleResult();
        return type;
    }



}
