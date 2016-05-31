package org.oregami.data;

import org.oregami.entities.GameTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GameTitleDao extends GenericDAOUUIDImpl<GameTitle, String> {

	@Autowired
	public GameTitleDao(EntityManager em) {
		super(em);
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
