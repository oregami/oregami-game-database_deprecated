package org.oregami.data;

import org.oregami.entities.datalist.BaseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseListDao<T extends BaseList> extends GenericDAOUUIDImpl<T, String> {

	@Autowired
	public BaseListDao(EntityManager em) {
		super(em);
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
