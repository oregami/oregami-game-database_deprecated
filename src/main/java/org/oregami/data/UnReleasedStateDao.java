package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.ReleaseType;
import org.oregami.entities.datalist.UnReleasedState;

import javax.persistence.EntityManager;

public class UnReleasedStateDao extends BaseListDao<UnReleasedState>{

	@Inject
	public UnReleasedStateDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=UnReleasedState.class;
	}

}
