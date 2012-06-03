package com.codenuance.poc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class KeyCount {

	public static void main(String args[]) {
		KeyCount keyCountPairFile = new KeyCount(new File(args[0]));
		keyCountPairFile.process();
	}

	private File keyCountFile;
	private Map<String, Integer> parsedData;

	public KeyCount(File file) {
		this.keyCountFile = file;
	}

	public void printSum() {
		Set<Entry<String, Integer>> entrySet = this.parsedData.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.printf("The total for %1s is %2s. ", entry.getKey(),
					entry.getValue());
		}
	}

	public void process() {
		this.sum();
		this.printSum();
	}

	public List<String> readToList() {
		FileReader fileReader;
		try {
			fileReader = new FileReader(this.keyCountFile);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		}

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> localKeyCountList = new ArrayList<String>();
		String line = null;

		try {
			while ((line = bufferedReader.readLine()) != null) {
				localKeyCountList.add(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return localKeyCountList;
	}

	public Map<String, Integer> sum() {
		Map<String, Integer> localParsedData = new LinkedHashMap<String, Integer>();
		for (String record : this.readToList()) {
			String[] split = record.split(",");
			String key = split[0];
			int num = Integer.parseInt(split[1]);
			if (localParsedData.containsKey(key)) {
				Integer count = localParsedData.get(key);
				localParsedData.put(key, count + num);
			} else {
				localParsedData.put(key, num);
			}
		}
		this.parsedData = localParsedData;
		return localParsedData;
	}

}
