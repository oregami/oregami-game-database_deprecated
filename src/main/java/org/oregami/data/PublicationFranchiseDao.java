package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.joda.time.LocalDateTime;
import org.oregami.entities.GameTitle;
import org.oregami.entities.PublicationFranchise;

import javax.persistence.EntityManager;
import java.util.List;

public class PublicationFranchiseDao extends GenericDAOUUIDImpl<PublicationFranchise, String>{

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
