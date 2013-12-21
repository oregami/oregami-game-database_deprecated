package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.datalist.GameEntryType;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class GameEntryTypeDao extends BaseListDao<GameEntryType>{

	@Inject
	public GameEntryTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=GameEntryType.class;
	}
	

}
