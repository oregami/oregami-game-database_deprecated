package org.oregami.data;

import org.oregami.entities.datalist.RemakeEnhancementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RemakeEnhancementTypeDao extends BaseListDao<RemakeEnhancementType>{

	@Autowired
	public RemakeEnhancementTypeDao(EntityManager em) {
		super(em);
		entityClass=RemakeEnhancementType.class;
	}

}
