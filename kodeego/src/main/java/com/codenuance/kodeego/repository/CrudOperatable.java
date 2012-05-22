package com.codenuance.kodeego.repository;

import javax.persistence.EntityManager;

public interface CrudOperatable<T> {

	public abstract T create(T entity);

	public abstract void delete(String entityId);

	public abstract void delete(T entity);

	public abstract EntityManager getEntityManager();

	public abstract T read(String id);

	public abstract T update(T entity);

}