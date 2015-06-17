package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import org.oregami.entities.PublicationFranchise;

import javax.persistence.EntityManager;

public class PublicationFranchiseDao extends GenericDAOUUIDImpl<PublicationFranchise, String>{

	@Inject
	public PublicationFranchiseDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=PublicationFranchise.class;
	}


    /*
    @SuppressWarnings("unchecked")
	public List<GameTitle> findByExactName(String name) {
        List<GameTitle> gameTitleList = getEntityManager()
        		.createNativeQuery("SELECT * FROM PublicationFranchiseDao t where lower(t.name) = :value ", PublicationFranchiseDao.class).setParameter("value", name.toLowerCase()).getResultList();
        return gameTitleList;
    }
    */

    @Override
    @Transactional
    public void update(PublicationFranchise entity) {
        super.update(entity);
    }

    @Override
    @Transactional
    public String save(PublicationFranchise entity) {
        return super.save(entity);
    }


}
