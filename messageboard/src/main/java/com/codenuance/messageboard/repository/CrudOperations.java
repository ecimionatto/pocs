package com.codenuance.messageboard.repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CrudOperations<T> {

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	private void assignGenericType() {
		if (entityClass == null) {
			@SuppressWarnings("rawtypes")
			Class<? extends CrudOperations> class1 = this.getClass();
			this.entityClass = ((Class<T>) ((ParameterizedType) class1
					.getGenericSuperclass()).getActualTypeArguments()[0]);
		}
	}

	public T create(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	public void delete(String entityId) {
		entityManager.remove(read(entityId));
		entityManager.flush();
	}

	public void delete(T entity) {
		entityManager.remove(entity);
		entityManager.flush();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public T read(String id) {
		assignGenericType();
		return entityManager.find(entityClass, id);
	}

	public T update(T entity) {
		T merged = entityManager.merge(entity);
		entityManager.flush();
		return merged;
	}

}
