package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.ReleaseGroupReason;

import javax.persistence.EntityManager;

public class ReleaseGroupReasonDao extends BaseListDao<ReleaseGroupReason>{

	@Inject
	public ReleaseGroupReasonDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=ReleaseGroupReason.class;
	}
	
}
