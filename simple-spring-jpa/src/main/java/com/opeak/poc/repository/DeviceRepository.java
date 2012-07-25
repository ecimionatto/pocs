package com.opeak.poc.repository;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.opeak.poc.model.Device;

@Transactional
@Repository
public class DeviceRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void createDevice(Device device) {
		entityManager.persist(device);
	}

	public Device findById(String mdi) {
		return entityManager.find(Device.class, mdi);
	}

	public Collection<Device> findAll() {
		TypedQuery<Device> createQuery = entityManager.createQuery(
				"select d from Device d", Device.class);
		return createQuery.getResultList();
	}

}
