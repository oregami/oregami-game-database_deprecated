package org.oregami.data;

import org.oregami.entities.datalist.ReleaseGroupReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ReleaseGroupReasonDao extends BaseListDao<ReleaseGroupReason> {

	@Autowired
	public ReleaseGroupReasonDao(EntityManager em) {
		super(em);
		entityClass=ReleaseGroupReason.class;
	}
	
}
