package org.oregami.data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.oregami.entities.BaseEntity;

import com.google.inject.Inject;

public abstract class GenericDAOImpl<E extends BaseEntity, P> implements
		GenericDAO<E, P> {

	@Inject
	public GenericDAOImpl(EntityManager man) {
		this.entityManager = man;
	}

	@Inject
	private EntityManager entityManager;
	
	Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public P save(E entity) {
		entityManager.persist(entity);
		return (P) entity.getId();
	}

	public E findOne(P id) {
		return entityManager.find(getEntityClass(), id);
	}

	public void update(E entity) {
		entityManager.merge(entity);
	}

	public void delete(E entity) {
		entityManager.remove(entity);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

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
		return this.entityManager.createNamedQuery(
				getEntityClass().getSimpleName() + ".GetAll").getResultList();
	}
	
	
	public EntityTransaction getTransaction() {
		return getEntityManager().getTransaction();
	}

}