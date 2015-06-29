package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.RemakeEnhancementType;

import javax.persistence.EntityManager;

public class RemakeEnhancementTypeDao extends BaseListDao<RemakeEnhancementType>{

	@Inject
	public RemakeEnhancementTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=RemakeEnhancementType.class;
	}

}
