package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.Language;

import javax.persistence.EntityManager;

public class LanguageDao extends GenericDAOUUIDImpl<Language, String>{

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
