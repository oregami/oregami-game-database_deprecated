package org.oregami.data;

import org.oregami.entities.GamingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GamingEnvironmentDao extends GenericDAOUUIDImpl<GamingEnvironment, String> {

	@Autowired
	public GamingEnvironmentDao(EntityManager em) {
		super(em);
		entityClass=GamingEnvironment.class;
	}
	
	
    @SuppressWarnings("unchecked")
	public List<GamingEnvironment> findByTitle(String title) {
		List<GamingEnvironment> geList = getEntityManager()
				.createNativeQuery("SELECT * FROM Gaming_Environment t where title = :value ", GamingEnvironment.class).setParameter("value", title).getResultList();
		return geList;
	}

	public GamingEnvironment findOneByExactTitle(String title) {
		GamingEnvironment ge = (GamingEnvironment) getEntityManager()
				.createNativeQuery("SELECT distinct g.* " +
                        " FROM " +
                        " GamingEnvironment g, " +
                        " GamingEnvironment_PlatformTitle gp, " +
                        " PlatformTitle p, " +
                        " TransliteratedString ts " +
                        " where g.id = gp.GamingEnvironment_id " +
                        " and gp.title_id = p.id " +
                        " and p.text_id = ts.id " +
                        " and ts.text = :value ", GamingEnvironment.class).setParameter("value", title).getSingleResult();
		return ge;
	}


}
