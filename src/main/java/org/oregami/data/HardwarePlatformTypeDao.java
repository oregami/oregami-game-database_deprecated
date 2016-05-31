package org.oregami.data;

import org.oregami.entities.datalist.HardwarePlatformType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HardwarePlatformTypeDao extends BaseListDao<HardwarePlatformType>{

	@Autowired
	public HardwarePlatformTypeDao(EntityManager em) {
		super(em);
		entityClass=HardwarePlatformType.class;
	}
	

}
