package org.oregami.data;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.oregami.entities.datalist.BaseList;

import javax.persistence.EntityManager;
import java.util.List;

public class BaseListDao<T extends BaseList> extends GenericDAOImpl<T, Long>{

	@Inject
	public BaseListDao(Provider<EntityManager> emf) {
		super(emf);
	}
	
	@SuppressWarnings("unchecked")
    public T findByName(String value) {
		//T type = (T)
        List resultList = getEntityManager()
                .createQuery("SELECT t FROM " + getEntityClass().getSimpleName() + " t where t.value = '" + value + "'").getResultList();
        if (resultList!=null && resultList.size()==1) {
            return (T) resultList.get(0);
        } else if (resultList.size()>1) {
            throw new RuntimeException("More than one result found");
        }
        return null;
    }
	
	

}
