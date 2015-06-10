package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.HardwarePlatformType;
import org.oregami.entities.datalist.SoftwarePlatformType;

import javax.persistence.EntityManager;

public class SoftwarePlatformTypeDao extends BaseListDao<SoftwarePlatformType>{

	@Inject
	public SoftwarePlatformTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=SoftwarePlatformType.class;
	}
	

}
