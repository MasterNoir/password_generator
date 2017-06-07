package de.masternoir.pw_gen;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import junit.framework.TestCase;

public class Password_Generator_test {
	
	private Password_Generator pw_gen = new Password_Generator();
	private Pattern pat;
	private Matcher mat;
	private boolean matches;
	
	private String[] charSets = {"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "0123456789", "\\p{Punct}"};
	
	@Test
    public void lessThan4CharsException() {
		
		try {
			pw_gen.getComplexPassword(3,"asd");
			fail("Excpected an PwTooShortException to be thrown.");
		} catch (PwTooShortException e) {
			assertEquals(e.getMessage(), "Length of pw: 3");
		}
    }
	
	@Test
    public void fourCharsTest() {
		
		String pw = "";
		
		matches = false;
		
		try {
			pw = pw_gen.getComplexPassword(4,"!!??");
			
		} catch (PwTooShortException e) {
			fail("No Exception should be thrown.");
		}
		
		
		for(String s : charSets){
			pat = Pattern.compile(".*[" + s + "].*");
			mat  = pat.matcher(pw);
			matches = mat.matches();
			
			assertEquals(matches, true);
		}
		
    }
	
	@Test
    public void tenCharsTest() {
		
		String pw;
		
		matches = false;
		
		pw = pw_gen.getSimplePw(10,"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		System.out.println(pw + "  Start");
		
		try {
			pw = pw_gen.getComplexPassword(10, pw);
			
		} catch (PwTooShortException e) {
			fail("No Exception should be thrown.");
		}
		
		System.out.println(pw + "  End");
		
		for(String s : charSets){
			pat = Pattern.compile(".*[" + s + "].*");
			mat  = pat.matcher(pw);
			matches = mat.matches();
			
			assertEquals(matches, true);
		}
		
    }
}
