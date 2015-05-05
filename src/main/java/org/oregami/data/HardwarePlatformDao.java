package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.HardwarePlatform;
import org.oregami.entities.TransliteratedString;

import javax.persistence.EntityManager;
import java.util.List;

public class HardwarePlatformDao extends GenericDAOUUIDImpl<HardwarePlatform, String>{

	@Inject
	public HardwarePlatformDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=HardwarePlatform.class;
	}


}
