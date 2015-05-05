package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.GamingEnvironment;
import org.oregami.entities.TransliteratedString;

import javax.persistence.EntityManager;
import java.util.List;

public class TransliteratedStringDao extends GenericDAOUUIDImpl<TransliteratedString, String>{

	@Inject
	public TransliteratedStringDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=TransliteratedString.class;
	}


    @SuppressWarnings("unchecked")
	public List<TransliteratedString> findByText(String text) {
        List<TransliteratedString> list = getEntityManager()
        		.createNativeQuery("SELECT * FROM TransliteratedString t where text = :value ", TransliteratedString.class).setParameter("value", text).getResultList();
        return list;
    }


}
