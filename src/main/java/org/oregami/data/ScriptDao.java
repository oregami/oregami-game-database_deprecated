package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.Script;

import javax.persistence.EntityManager;

public class ScriptDao extends BaseListDao<Script>{

	@Inject
	public ScriptDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=Script.class;
	}
	
	public Script findByExactName(String name) {
    	Script l = (Script) getEntityManager()
        		.createNativeQuery("SELECT * FROM Script t where lower(t.value) = :value ", Script.class).setParameter("value", name.toLowerCase()).getSingleResult(); 
        return l;
    }
}
