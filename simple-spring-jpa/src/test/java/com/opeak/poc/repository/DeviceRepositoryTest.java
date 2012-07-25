package com.opeak.poc.repository;

import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.opeak.poc.model.Device;

@Transactional
public class DeviceRepositoryTest extends RepositoryIntegrationTest {

	@Autowired
	private DeviceRepository deviceRepository;

	@Test
	public void shouldCreateDevice() {
		Device device = new Device();
		device.setName("device test");
		deviceRepository.createDevice(device);

		Device persistedDevice = deviceRepository.findById(device.getMdi());
		assertThat(device, CoreMatchers.equalTo(persistedDevice));
	}

	@Test
	public void shouldFindAll() {
		
		Device device = new Device();
		device.setName("device test 1");
		deviceRepository.createDevice(device);
		
		Device device2 = new Device();
		device2.setName("device test 2");
		deviceRepository.createDevice(device2);
		
		Device device3 = new Device();
		device3.setName("device test 3");
		deviceRepository.createDevice(device3);
		
		Collection<Device> devices = deviceRepository.findAll();
		assertThat(devices.size(), CoreMatchers.equalTo(3));
	}
}
