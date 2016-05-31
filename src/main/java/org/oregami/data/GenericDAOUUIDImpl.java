package org.oregami.data;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.joda.time.LocalDateTime;
import org.oregami.entities.BaseEntityUUID;
import org.oregami.entities.CustomRevisionEntity;
import org.oregami.entities.CustomRevisionListener;
import org.oregami.entities.TopLevelEntity;
import org.oregami.service.ServiceCallContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public abstract class GenericDAOUUIDImpl<E extends BaseEntityUUID, P> implements
        GenericDAOUUID<E, P> {

    private final EntityManager em;

    @Autowired
    public GenericDAOUUIDImpl(EntityManager em) {
        this.em=em;
    }

    Class<E> entityClass;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public P save(E entity) {
        entity.setChangeTime(new LocalDateTime());
        em.persist(entity);
        updateRevisionListener(entity);
        return (P) entity.getId();
    }

    @Override
    public E findOne(P id) {
        return em.find(getEntityClass(), id);
    }

    @Override
    @Transactional
    public void update(E entity) {
        updateRevisionListener(entity);
        entity.setChangeTime(new LocalDateTime());
        em.merge(entity);
    }

    @Override
    @Transactional
    public void delete(E entity) {
        em.remove(entity);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        if (entityClass == null) {
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType paramType = (ParameterizedType) type;

                entityClass = (Class<E>) paramType.getActualTypeArguments()[0];

            } else {
                throw new IllegalArgumentException(
                        "Could not guess entity class by reflection");
            }
        }
        return entityClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findAll() {
        return this.em.createNamedQuery(
                getEntityClass().getSimpleName() + ".GetAll").getResultList();
    }


    public EntityTransaction getTransaction() {
        return getEntityManager().getTransaction();
    }

    protected void updateRevisionListener(BaseEntityUUID entity) {
        if (entity.getClass().isAnnotationPresent(TopLevelEntity.class)) {
            ServiceCallContext context = CustomRevisionListener.context.get();
            if (context != null) {
                context.setEntityDiscriminator(entity.getClass().getAnnotation(TopLevelEntity.class).discriminator());
                context.setEntityId(entity.getId());
            }
        }
    }

    public List<RevisionInfo> findRevisions(String id) {
        List<RevisionInfo> list = new ArrayList<>();
        AuditReader reader = AuditReaderFactory.get(getEntityManager());
        List<Number> revisions = reader.getRevisions(getEntityClass(), id);
        for (Number n : revisions) {
            CustomRevisionEntity revEntity = reader.findRevision(CustomRevisionEntity.class, n);
            list.add(new RevisionInfo(n, revEntity));
        }
        return list;
    }

    public E findRevision(String id, Number revision) {
        AuditReader reader = AuditReaderFactory.get(getEntityManager());
        List<Number> revisions = reader.getRevisions(getEntityClass(), id);
        if (!revisions.contains(revision)) {
            return null;
        }
        E entity = reader.find(getEntityClass(), id, revision);
        return entity;
    }

}
