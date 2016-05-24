package org.oregami.data;

import org.oregami.entities.datalist.GameEntryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GameEntryTypeDao extends BaseListDao<GameEntryType> {

	@Autowired
	public GameEntryTypeDao(EntityManager em) {
		super(em);
		entityClass=GameEntryType.class;
	}


}
