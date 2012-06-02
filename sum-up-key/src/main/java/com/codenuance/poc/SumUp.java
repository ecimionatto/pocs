package com.codenuance.poc;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SumUp {

	private List<String> originalData;
	private Map<String, Integer> parsedData;

	public List<String> getOriginalData() {
		return originalData;
	}

	public SumUp(List<String> originalData) {
		this.originalData = originalData;
	}

	public Map<String, Integer> sum() {
		parsedData = new LinkedHashMap<String, Integer>();
		for (String record : originalData) {
			String[] split = record.split(",");
			String key = split[0];
			int num = Integer.parseInt(split[1]);
			if (parsedData.containsKey(key)) {
				Integer count = parsedData.get(key);
				parsedData.put(key, count + num);
			} else {
				parsedData.put(key, num);
			}
		}
		return parsedData;
	}

	public void printSum() {
		Set<Entry<String, Integer>> entrySet = this.parsedData.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.printf("The total for %1s is %2s. ", entry.getKey(),
					entry.getValue());
		}

	}

}
