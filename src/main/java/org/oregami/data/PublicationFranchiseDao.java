package org.oregami.data;

import java.util.List;

import javax.persistence.EntityManager;

import org.oregami.entities.GameTitle;
import org.oregami.entities.PublicationFranchise;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class PublicationFranchiseDao extends GenericDAOImpl<PublicationFranchise, Long>{

	@Inject
	public PublicationFranchiseDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=PublicationFranchise.class;
	}
	
	
    @SuppressWarnings("unchecked")
	public List<GameTitle> findByExactName(String name) {
        List<GameTitle> gameTitleList = getEntityManager()
        		.createNativeQuery("SELECT * FROM PublicationFranchiseDao t where lower(t.name) = :value ", PublicationFranchiseDao.class).setParameter("value", name.toLowerCase()).getResultList(); 
        return gameTitleList;
    }


}
