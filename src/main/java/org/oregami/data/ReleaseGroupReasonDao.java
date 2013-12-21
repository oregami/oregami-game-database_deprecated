package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.datalist.ReleaseGroupReason;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ReleaseGroupReasonDao extends BaseListDao<ReleaseGroupReason>{

	@Inject
	public ReleaseGroupReasonDao(Provider<EntityManager> emf) {
		super(emf);
	}
	
}
