package org.oregami.data;

import org.oregami.entities.datalist.TitleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TitleTypeDao extends BaseListDao<TitleType>{

	@Autowired
	public TitleTypeDao(EntityManager em) {
		super(em);
		entityClass=TitleType.class;
	}
	

}
