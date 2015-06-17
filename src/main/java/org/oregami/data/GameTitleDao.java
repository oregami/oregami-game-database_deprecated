package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.GameTitle;

import javax.persistence.EntityManager;

public class GameTitleDao extends GenericDAOUUIDImpl<GameTitle, String>{

	@Inject
	public GameTitleDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=GameTitle.class;
	}


	/*
    @SuppressWarnings("unchecked")
	public List<GameTitle2> findByExactName(String name) {
        List<GameTitle2> gameTitleList = getEntityManager()
        		.createNativeQuery("SELECT * FROM GameTitle t where lower(t.nativeSpelling) = :value ", GameTitle2.class).setParameter("value", name.toLowerCase()).getResultList();
        return gameTitleList;
    }
    */


}
