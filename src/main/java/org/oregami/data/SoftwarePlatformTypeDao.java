package org.oregami.data;

import org.oregami.entities.datalist.SoftwarePlatformType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SoftwarePlatformTypeDao extends BaseListDao<SoftwarePlatformType>{

	@Autowired
	public SoftwarePlatformTypeDao(EntityManager em) {
		super(em);
		entityClass=SoftwarePlatformType.class;
	}
	

}
