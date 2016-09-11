package org.oregami.data;

import org.oregami.domain.model.publicationFranchise.PublicationFranchise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PublicationFranchiseDao extends GenericDAOUUIDImpl<PublicationFranchise, String> {

	@Autowired
	public PublicationFranchiseDao(EntityManager em) {
		super(em);
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
