package org.oregami.data;

import org.oregami.entities.CustomRevisionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RevisionEntityDao {

    @Autowired
    public final EntityManagerFactory emf = null;

    public List<CustomRevisionEntity> findAll() {
        int limit = 100;
        Query query = emf.createEntityManager().createQuery(
                "SELECT e FROM CustomRevisionEntity e order by timestamp DESC")
                .setMaxResults(limit);
        return (List<CustomRevisionEntity>) query.getResultList();
    }

}
