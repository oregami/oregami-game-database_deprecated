package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.GamingEnvironment;

import javax.persistence.EntityManager;
import java.util.List;

public class GamingEnvironmentDao extends GenericDAOUUIDImpl<GamingEnvironment, String>{

	@Inject
	public GamingEnvironmentDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=GamingEnvironment.class;
	}
	
	
    @SuppressWarnings("unchecked")
	public List<GamingEnvironment> findByTitle(String title) {
		List<GamingEnvironment> geList = getEntityManager()
				.createNativeQuery("SELECT * FROM GamingEnvironment t where title = :value ", GamingEnvironment.class).setParameter("value", title).getResultList();
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
