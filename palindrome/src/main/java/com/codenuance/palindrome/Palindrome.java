package com.codenuance.palindrome;

public class Palindrome {

	public boolean check(String word) {
		word = word.replaceAll("[^A-Za-z0-9w]", "");
		word = word.toLowerCase();

		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) != word.charAt(word.length() - (i + 1))) {
				return false;
			}
		}
		return true;
	}

}
