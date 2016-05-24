package org.oregami.data;

import org.oregami.entities.datalist.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ScriptDao extends BaseListDao<Script>{

	@Autowired
	public ScriptDao(EntityManager em) {
		super(em);
		entityClass=Script.class;
	}
	
	public Script findByExactName(String name) {
    	Script l = (Script) getEntityManager()
        		.createNativeQuery("SELECT * FROM Script t where lower(t.value) = :value ", Script.class).setParameter("value", name.toLowerCase()).getSingleResult();
        return l;
    }
}
