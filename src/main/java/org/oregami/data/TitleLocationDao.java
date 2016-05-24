package org.oregami.data;

import org.oregami.entities.datalist.TitleLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TitleLocationDao extends BaseListDao<TitleLocation>{

	@Autowired
	public TitleLocationDao(EntityManager em) {
		super(em);
		entityClass=TitleLocation.class;
	}

}
