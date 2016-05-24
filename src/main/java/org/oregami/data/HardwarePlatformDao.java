package org.oregami.data;

import org.oregami.entities.HardwarePlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class HardwarePlatformDao extends GenericDAOUUIDImpl<HardwarePlatform, String> {

	@Autowired
	public HardwarePlatformDao(EntityManager em) {
		super(em);
		entityClass=HardwarePlatform.class;
	}


}
