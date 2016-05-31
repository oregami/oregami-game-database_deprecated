package org.oregami.data;

import org.oregami.entities.datalist.UnReleasedState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UnReleasedStateDao extends BaseListDao<UnReleasedState> {

	@Autowired
	public UnReleasedStateDao(EntityManager em) {
		super(em);
		entityClass=UnReleasedState.class;
	}

}
