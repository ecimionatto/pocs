package com.codenuance.palindrome;

public class Palindrome {

	public boolean check(String word) {
		String cleanedWord = word.replaceAll("[^A-Za-z0-9w]", "");
		cleanedWord = cleanedWord.toLowerCase();

		for (int i = 0; i < cleanedWord.length(); i++) {
			char oppositeCharacter = cleanedWord.charAt(cleanedWord.length() - (i + 1));
			if (cleanedWord.charAt(i) != oppositeCharacter) {
				return false;
			}
		}
		return true;
	}

}
