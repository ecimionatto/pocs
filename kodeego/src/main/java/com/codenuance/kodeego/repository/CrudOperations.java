package com.codenuance.kodeego.repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CrudOperations<T> implements CrudOperatable<T> {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codenuance.kodeego.repository.CrudOperatable#create(T)
	 */
	public T create(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.codenuance.kodeego.repository.CrudOperatable#delete(java.lang
	 * .String)
	 */
	public void delete(String entityId) {
		entityManager.remove(read(entityId));
		entityManager.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codenuance.kodeego.repository.CrudOperatable#delete(T)
	 */
	public void delete(T entity) {
		entityManager.remove(entity);
		entityManager.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.codenuance.kodeego.repository.CrudOperatable#getEntityManager()
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.codenuance.kodeego.repository.CrudOperatable#read(java.lang.
	 * String)
	 */
	public T read(String id) {
		assignGenericType();
		return entityManager.find(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.codenuance.kodeego.repository.CrudOperatable#update(T)
	 */
	public T update(T entity) {
		T merged = entityManager.merge(entity);
		entityManager.flush();
		return merged;
	}

}
