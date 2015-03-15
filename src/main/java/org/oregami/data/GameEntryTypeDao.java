package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.GameEntryType;

import javax.persistence.EntityManager;

public class GameEntryTypeDao extends BaseListDao<GameEntryType>{

	@Inject
	public GameEntryTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=GameEntryType.class;
	}
	

}
