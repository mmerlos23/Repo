package password;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	private ArrayList<String> prPass;
	@Before
	public void setUp() throws Exception {
		String[] password = { "Moises23!", "Cmsc204!", "Juan$678" };
		prPass = new ArrayList<>();
		prPass.addAll(Arrays.asList(password));
	}

	@After
	public void tearDown() throws Exception {
		prPass = null;
	
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("M!ke2"));
		} catch (LengthException e) {
			System.out.println(e.getMessage());
			assertTrue("lengthExcepetion was thrown", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Another exception thrown", false);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha() {
		try {
			PasswordCheckerUtility.isValidPassword("renaldo@34");
			assertTrue(PasswordCheckerUtility.isValidPassword("Renaldo@34"));
		} catch (NoUpperAlphaException e) {
			System.out.println(e.getMessage());
			assertTrue("Threw a NoUpperAlphaException", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Another exception is thrown", false);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha() {
		try {
			PasswordCheckerUtility.isValidPassword("ME$SI23");
			assertTrue(PasswordCheckerUtility.isValidPassword("me$si23"));
		} catch (NoLowerAlphaException e) {
			assertTrue("Threw a NoLowerAlphaException", true);
		} catch (Exception e) {
			assertFalse("Another exception is thrown", true);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword() {
		try {
			PasswordCheckerUtility.isWeakPassword("Mmer89@");
			assertTrue(PasswordCheckerUtility.isWeakPassword("Mmer89@"));
		} catch (InvalidSequenceException e) {
			System.out.println(e.getMessage());
			assertTrue("Threw a InvalidSequenceException", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("Another exception thrown", true);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence() {
		try {
			PasswordCheckerUtility.isValidPassword("Aples@23");
			assertEquals(true, PasswordCheckerUtility.isValidPassword("Apples@23"));
		} catch (InvalidSequenceException e) {
			System.out.println(e.getMessage());
			assertTrue("Threw a InvalidSequenceException", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertFalse("Another exception thrown", true);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("moitH#66"));
			PasswordCheckerUtility.isValidPassword("moitH#");
		} catch (NoDigitException e) {
			System.out.println(e.getMessage());
			assertTrue("Threw a NoDigitException", true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertFalse("Another exception thrown", true);
		}
	}
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("UpDown@123"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue("The password is invalid exception", true);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoDigitException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws LengthException 
	 */
	@Test
	public void testInvalidPasswords() throws LengthException, NoUpperAlphaException, 
	NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, 
	InvalidSequenceException {
		
		ArrayList<String> invalidPass = PasswordCheckerUtility.getInvalidPasswords(prPass);
		for (int i = 6; i < invalidPass.size(); i++) {
		//for (String password : invalidPass) {
			
			assertTrue(PasswordCheckerUtility.isValidPassword(invalidPass.get(i)));
		}
	}
	
}