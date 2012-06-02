package com.codenuance.poc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KeyCountPairFile {

	private File keyCountFile;

	public KeyCountPairFile(File file) {
		this.keyCountFile = file;
	}

	public List<String> readToList() {
		FileReader fileReader;
		try {
			fileReader = new FileReader(this.keyCountFile);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(e);
		}

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;

		try {
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

		return lines;
	}

	public static void main(String args[]) {
		KeyCountPairFile keyCountPairFile = new KeyCountPairFile(new File(
				args[0]));
		List<String> readIntoList = keyCountPairFile.readToList();
		SumUp sumUp = new SumUp(readIntoList);
		sumUp.sum();
		sumUp.printSum();
	}

}
