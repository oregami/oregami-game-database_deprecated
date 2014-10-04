package org.oregami.data;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.persist.Transactional;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.joda.time.LocalDateTime;
import org.oregami.entities.GameTitle;
import org.oregami.entities.PublicationFranchise;

import com.google.inject.Inject;
import com.google.inject.Provider;

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

    public List<Number> findRevisions(String id) {

        AuditReader reader = AuditReaderFactory.get(getEntityManager());
        List<Number> revisions = reader.getRevisions(PublicationFranchise.class, id);
        return revisions;

    }

    public PublicationFranchise findRevision(String id, Number revision) {

        AuditReader reader = AuditReaderFactory.get(getEntityManager());

        List<Number> revisions = reader.getRevisions(PublicationFranchise.class, id);
        if (!revisions.contains(revision)) {
            return null;
        }
        PublicationFranchise tRev = reader.find(PublicationFranchise.class, id, revision);
        return tRev;

    }

    @Override
    @Transactional
    public void update(PublicationFranchise entity) {
        entity.setChangeTime(new LocalDateTime());
        super.update(entity);
    }

    @Override
    @Transactional
    public String save(PublicationFranchise entity) {
        entity.setChangeTime(new LocalDateTime());
        return super.save(entity);
    }


}
