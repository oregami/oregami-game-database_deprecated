package org.oregami.data;

import org.oregami.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LanguageDao extends GenericDAOUUIDImpl<Language, String>{

	@Autowired
	public LanguageDao(EntityManager em) {
		super(em);
		entityClass=Language.class;
	}
	
	
	public Language findByExactName(String name) {
        Language l = (Language) getEntityManager()
        		.createNativeQuery("SELECT * FROM Language t where lower(t.name) = :value ", Language.class).setParameter("value", name.toLowerCase()).getSingleResult();
        return l;
    }


}
