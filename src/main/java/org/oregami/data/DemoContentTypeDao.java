package org.oregami.data;

import org.oregami.entities.datalist.DemoContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DemoContentTypeDao extends BaseListDao<DemoContentType> {

	@Autowired
	public DemoContentTypeDao(EntityManager em) {
		super(em);
		entityClass=DemoContentType.class;
	}

}
