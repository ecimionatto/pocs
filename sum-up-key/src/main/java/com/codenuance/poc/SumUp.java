package com.codenuance.poc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumUp {

	private List<String> originalData;

	public List<String> getOriginalData() {
		return originalData;
	}

	public SumUp(List<String> originalData) {
		this.originalData = originalData;
	}

	public Map<String, Integer> sum() {
		Map<String, Integer> parsedData = new HashMap<String, Integer>();
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

}
