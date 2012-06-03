package com.codenuance.palindrome;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeTest {

	@Test
	public void shouldCheckAndReturnsFalse() {
		Palindrome palindrome = new Palindrome();
		assertFalse(palindrome.check("test"));
	}

	@Test
	public void shouldCheckAhSatanseesNatashaAndReturnstrue() {
		Palindrome palindrome = new Palindrome();
		assertTrue(palindrome.check("Ah, Satan sees Natasha"));
	}

	@Test
	public void shouldCheckAhPopAndReturnstrue() {
		Palindrome palindrome = new Palindrome();
		assertTrue(palindrome.check("pop"));
	}

	
}
