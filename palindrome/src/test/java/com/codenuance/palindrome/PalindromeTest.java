package com.codenuance.palindrome;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeTest {

	@Test
	public void shouldCheckTest_ReturnsFalse() {
		Palindrome palindrome = new Palindrome();
		assertFalse(palindrome.check("test"));
	}

	@Test
	public void shouldCheckAh_Satan_sees_Natasha_Returnstrue() {
		Palindrome palindrome = new Palindrome();
		assertTrue(palindrome.check("Ah, Satan sees Natasha"));
	}

	@Test
	public void shouldCheckAh_Pop_Returnstrue() {
		Palindrome palindrome = new Palindrome();
		assertTrue(palindrome.check("pop"));
	}

	
}
