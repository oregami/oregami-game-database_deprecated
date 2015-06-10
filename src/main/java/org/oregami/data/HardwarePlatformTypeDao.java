package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.HardwarePlatformType;
import org.oregami.entities.datalist.TitleType;

import javax.persistence.EntityManager;

public class HardwarePlatformTypeDao extends BaseListDao<HardwarePlatformType>{

	@Inject
	public HardwarePlatformTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=HardwarePlatformType.class;
	}
	

}
