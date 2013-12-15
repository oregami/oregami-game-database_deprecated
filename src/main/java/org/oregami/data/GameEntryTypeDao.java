package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.datalist.GameEntryType;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class GameEntryTypeDao extends GenericDAOImpl<GameEntryType, Long>{

	@Inject
	public GameEntryTypeDao(Provider<EntityManager> emf) {
		super(emf);
		entityClass=GameEntryType.class;
	}
	
    public GameEntryType findByName(String value) {
    	GameEntryType type = (GameEntryType)getEntityManager()
                .createQuery("SELECT t FROM GameEntryType t where t.value = '" + value + "'").getSingleResult(); 
        return type;
    }
	
	

}
