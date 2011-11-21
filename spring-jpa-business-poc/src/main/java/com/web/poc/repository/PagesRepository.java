package com.web.poc.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.web.poc.entity.Page;

@Repository
public class PagesRepository {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Page> findAll() {
		Query query = entityManager.createQuery("from Page");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Page> findByParent(String parentName) {
		Query createNamedQuery = entityManager
				.createNamedQuery(Page.BY_PARENT_NAME);
		createNamedQuery.setParameter("parent", parentName);
		return createNamedQuery.getResultList();
	}

	public void persist(Page page) {
		entityManager.persist(page);
	}

	public void bulkPersist(List<Page> pages) {
		for (Page page : pages) {
			entityManager.persist(page);
			if (page.getId() % 1000 == 0) {
				entityManager.flush();
			}
		}
	}

	public void update(Page page) {
		entityManager.merge(page);
	}

	public void bulkUpdate(List<Page> pages) {
		for (Page page : pages) {
			entityManager.merge(page);
			if (page.getId() % 1000 == 0) {
				entityManager.flush();
			}
		}
	}

	public void remove(Page page) {
		entityManager.remove(page);
	}

	public void bulkRemove(List<Page> pages) {
		for (Page page : pages) {
			if (page != null) {
				entityManager.remove(page);
			}
		}
	}
}
