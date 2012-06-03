package com.codenuance.poc;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class KeyCountTest {

	@Spy
	@InjectMocks
	KeyCount keyCount = new KeyCount(new File("test"));

	@Test
	public void integrationTest() {
		KeyCount.main(new String[] { "keycount.csv" });
	}

	@Test
	public void shouldProcess() {
		doReturn(null).when(keyCount).sum();
		doNothing().when(keyCount).printSum();
		keyCount.process();
		verify(keyCount).sum();
		verify(keyCount).printSum();
	}

	@Test
	public void shouldSum() {
		List<String> data = new ArrayList<String>();
		data.add("John,2");
		data.add("Jane,3");
		data.add("John,4");
		data.add("Jane,5");

		doReturn(data).when(keyCount).readToList();
		Map<String, Integer> sum = keyCount.sum();

		assertThat(sum.size(), CoreMatchers.equalTo(2));
		assertThat(sum.keySet(), JUnitMatchers.hasItems("John", "Jane"));
		assertThat(sum.get("John"), CoreMatchers.equalTo(6));
		assertThat(sum.get("Jane"), CoreMatchers.equalTo(8));
	}

}
