package org.oregami.data;

import javax.persistence.EntityManager;

import org.oregami.entities.datalist.BaseList;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BaseListDao<T extends BaseList> extends GenericDAOImpl<T, Long>{

	@Inject
	public BaseListDao(Provider<EntityManager> emf) {
		super(emf);
	}
	
	@SuppressWarnings("unchecked")
    public T findByName(String value) {
		T type = (T)getEntityManager()
                .createQuery("SELECT t FROM " + getEntityClass().getSimpleName() + " t where t.value = '" + value + "'").getSingleResult(); 
        return type;
    }
	
	

}
