package com.codenuance.poc;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

public class SumUpTest {

	@Test
	public void shouldSum() {
		List<String> data = new ArrayList<String>();
		data.add("John,2");
		data.add("Jane,3");
		data.add("John,4");
		data.add("Jane,5");
		SumUp sumUp = new SumUp(data);

		Map<String, Integer> sum = sumUp.sum();

		assertThat(sum.size(), CoreMatchers.equalTo(2));
		assertThat(sum.keySet(), JUnitMatchers.hasItems("John", "Jane"));
		assertThat(sum.get("John"), CoreMatchers.equalTo(6));
		assertThat(sum.get("Jane"), CoreMatchers.equalTo(8));
	}
}
