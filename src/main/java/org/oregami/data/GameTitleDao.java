package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.GameTitle;

import javax.persistence.EntityManager;
import java.util.List;

public class GameTitleDao extends GenericDAOUUIDImpl<GameTitle, String>{

	@Inject
	public GameTitleDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=GameTitle.class;
	}
	
	
    @SuppressWarnings("unchecked")
	public List<GameTitle> findByExactName(String name) {
        List<GameTitle> gameTitleList = getEntityManager()
        		.createNativeQuery("SELECT * FROM GameTitle t where lower(t.nativeSpelling) = :value ", GameTitle.class).setParameter("value", name.toLowerCase()).getResultList(); 
        return gameTitleList;
    }


}
