package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.ReleaseState;
import org.oregami.entities.datalist.UnReleasedState;

import javax.persistence.EntityManager;

public class ReleaseStateDao extends BaseListDao<ReleaseState>{

	@Inject
	public ReleaseStateDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=ReleaseState.class;
	}

}
