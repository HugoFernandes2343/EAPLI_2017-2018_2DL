/**
 *
 */
package eapli.util;

import java.util.Random;

/**
 * utility class for string manipulation
 *
 * @author Paulo Gandra Sousa
 *
 */
public class Strings {

    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private Strings() {
	// to make sure this is an utility class
    }

    /**
     * checks whether a String is empty (zero length or all spaces) or null
     *
     * @param text
     * @return
     */
    public static boolean isNullOrEmpty(String text) {
	return text == null || text.isEmpty();
    }

    /**
     * checks whether a String is empty (zero length or all spaces) or null
     *
     * @param text
     * @return
     */
    public static boolean isNullOrWhiteSpace(final String text) {
	return text == null || text.trim().isEmpty();
    }

    public static String randomString(int len) {
	return randomString(len, CHARSET);
    }

    public static String randomString(int len, String charSet) {
	final Random rnd = new Random();
	final StringBuilder sb = new StringBuilder();
	for (int i = 0; i < len; i++) {
	    final int c = rnd.nextInt(charSet.length());
	    sb.append(charSet.charAt(c));
	}
	return sb.toString();
    }

    public static String truncate(String org, int len) {
	if (len < org.length()) {
	    return org.substring(0, len);
	} else {
	    return org;
	}
    }

    public static String left(String org, int len) {
	return org.substring(0, len);
    }

    public static String right(String org, int len) {
	return org.substring(org.length() - len);
    }

    public static boolean containsDigit(String password) {
	return password.matches(".*\\d.*");
    }

    public static boolean containsAlpha(String password) {
	return password.matches(".*[a-zA-Z].*");
    }

    public static boolean containsCapital(String password) {
	return password.matches(".*[A-Z].*");
    }
}
