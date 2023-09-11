package password;

import java.util.ArrayList;

/**
 * @author Moises Merlos
 * @date 9/10/2023
 */
public class PasswordCheckerUtility {
	/*
	 * will check if password is at least 6 characters long
	 * 
	 * @param password
	 * 
	 * @return true if password is at least 6 characters long and false otherwise
	 * 
	 * @throws LengthException
	 */
    public static boolean isValidLength(String password) throws LengthException {
        if (password.length() < 6) {
            throw new LengthException();
        }
        return true;
    }
    /*
	 * will check if password has 1 numeric character
	 * 
	 * @param password
	 * 
	 * @return true if password has 1 numeric character and false otherwise
	 * 
	 * @throws NoDigitException
	 */
    public static boolean hasDigits(String password) throws NoDigitException {
        int digitCount = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                digitCount++;
            }
        }
        if (digitCount <= 0) {
            throw new NoDigitException();
        }
        return true;
    }
    /*
	 * @param password, passwordC throws an exception if password aren't the same
	 * @returns true if password 1 and 2 match and throws an exception otherwise
	 * @throws UnmatchedException
	 */
    public static void comparePasswords(String password, String passwordCon) throws UnmatchedException {
        if (!password.equals(passwordCon)) {
            throw new UnmatchedException();
        }
    }
    /*
	 * @password, passwordC
	 * 
	 * @return true if passwords match and false otherwise
	 * 
	 */
    public static boolean comparePasswordsWithReturn(String password, String passwordCon) {
        if (password.equals(passwordCon)) {
            return true;
        }
        return false;
    }
    /*
	 * will check if there is at least 1 upper case
	 * 
	 * @param password
	 * 
	 * @return true if password has at least one 1 upper case letter and false
	 * otherwise
	 * 
	 * @throws NoUpperAlphaException
	 */
    public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }
        throw new NoUpperAlphaException();
    }
    /*
	 * will check if there is at least 1 lower case
	 * 
	 * @param password
	 * 
	 * @return true if password has at least one lower case password and false
	 * otherwise
	 * 
	 * @throws NoLowerAlphaException
	 */
    public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                return true;
            }
        }
        throw new NoLowerAlphaException();
    }
    /*
	 * will check to see if no more than 2 of the same characters occur in a
	 * sequence
	 * 
	 * @param password
	 * 
	 * @return true if there is no more than 2 of the same characters that occur in
	 * a sequence
	 * 
	 * @throws InvalidSequenceException
	 */
    public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {
        int numbCount = 0;
        int sameC = 1;
        while (numbCount < password.length() - 1) {
            if (password.charAt(numbCount) == password.charAt(numbCount + 1)) {
                sameC += 1;
                if (sameC > 2) {
                    throw new InvalidSequenceException();
                }
            } else {
                sameC = 1;
            }
            numbCount++;
        }
        return true;
    }
    /*
	 * Checks if password is weak
	 * 
	 * @param string
	 * 
	 * @return true if password is between 6 and 9 characters
	 */
    public static boolean hasBetweenSixAndNineChars(String password) {
        int PassLength = password.length();
        return PassLength >= 6 && PassLength <= 9;
    }
    /*
	 * @param password check for a special character
	 * 
	 * @return true if there is at least one character and false otherwise
	 * 
	 * @throws NoSpecialCharacterException
	 */
    public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
        boolean SpecialC = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                SpecialC = true;
            }
        }
        if (SpecialC) {
            return true;
        }
        throw new NoSpecialCharacterException();
    }
    /*
	 * Will check the validity of one password, will throw one or more exceptions
	 * 
	 * @param String password
	 * 
	 * @return true if password is valid, otherwise return false if password is
	 * invalid
	 * 
	 * @throw WeakPasswordException LengthException, NoDigitException,
	 * NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException,
	 * NoSpecialCharacterException
	 */
    public static boolean isValidPassword(String password)
            throws LengthException, NoUpperAlphaException, NoLowerAlphaException,
            NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
        boolean Valid = true;

        if (!isValidLength(password)) {
            Valid = false;
            throw new LengthException();
        }
        if (!hasUpperAlpha(password)) {
            Valid = false;
            throw new NoUpperAlphaException();
        }
        if (!hasLowerAlpha(password)) {
            Valid = false;
            throw new NoLowerAlphaException();
        }
        if (!hasDigits(password)) {
            Valid = false;
            throw new NoDigitException();
        }
        if (!hasSpecialChar(password)) {
            Valid = false;
            throw new NoSpecialCharacterException();
        }
        if (!NoSameCharInSequence(password)) {
            Valid = false;
            throw new InvalidSequenceException();
        }
        return Valid;
    }
    /*
	 * @param String password will check if password is valid and the length is not
	 * between 6-9 characters
	 * 
	 * @return false if password is valid and length of password id not between 6-9
	 * characters
	 * 
	 * @throw WeakPasswordException LengthException, NoDigitException,
	 * NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException,
	 * NoSpecialCharacterException
	 */
    public static boolean isWeakPassword(String password)
            throws WeakPasswordException, LengthException, NoDigitException,
            NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException,
            NoSpecialCharacterException {
        int passLength = password.length();
        if (passLength >= 6 && passLength <= 9) {
            throw new WeakPasswordException();
        }
        return false;
    }
    /*
	 * @param : ArrayList of passwords
	 * 
	 * @return ArrayList with status of any invalid passwords
	 */
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> Checker = new ArrayList<>();
        for (int i = 0; i < passwords.size(); i++) {
            try {
                isValidPassword(passwords.get(i));
            } catch (Exception e) {
                Checker.add(passwords.get(i) + " " + e.getMessage());
            }
        }
        return Checker;
    }
}


