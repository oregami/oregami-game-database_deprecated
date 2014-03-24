package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.Language;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class LanguageDao extends GenericDAOImpl<Language, Long>{

	@Inject
	public LanguageDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=Language.class;
	}
	
	
	public Language findByExactName(String name) {
        Language l = (Language) getEntityManager()
        		.createNativeQuery("SELECT * FROM Language t where lower(t.name) = :value ", Language.class).setParameter("value", name.toLowerCase()).getSingleResult(); 
        return l;
    }


}
