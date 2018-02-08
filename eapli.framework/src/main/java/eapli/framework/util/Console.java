/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for reading different data types from the Console.
 *
 * based on code from Nuno Silva
 *
 * @author Paulo Gandra Sousa
 *
 */
@SuppressWarnings("squid:S106")
public final class Console {

	private Console() {
		// to make sure this is an utility class
	}

	public static String readLine(String prompt) {
		try {
			System.out.println(prompt);
			final InputStreamReader converter = new InputStreamReader(System.in);
			final BufferedReader in = new BufferedReader(converter);

			return in.readLine();
		} catch (final IOException ex) {
			Logger.getLogger(Console.class.getName()).log(Level.WARNING, null, ex);
			return null;
		}
	}

	public static int readInteger(String prompt) {
		do {
			try {
				final String input = readLine(prompt);
				return Integer.parseInt(input);
			} catch (final NumberFormatException ex) {
				// nothing to do
			}
		} while (true);
	}

	public static long readLong(String prompt) {
		do {
			try {
				final String input = readLine(prompt);
				return Long.parseLong(input);
			} catch (final NumberFormatException ex) {
				// nothing to do
			}
		} while (true);
	}

	public static boolean readBoolean(String prompt) {
		do {
			try {
				final String strBool = readLine(prompt).toLowerCase();
				if ("y".equals(strBool) || "s".equals(strBool)) {
					return true;
				} else if ("n".equals(strBool)) {
					return false;
				}
			} catch (final NumberFormatException ex) {
				// nothing to do
			}
		} while (true);
	}

	public static int readOption(int low, int high, int exit) {
		int option;
		do {
			option = Console.readInteger("Select an option: ");
			if (option == exit) {
				break;
			}
		} while (option < low || option > high);
		return option;
	}

	public static Date readDate(String prompt) {
		return readDate(prompt, "yyyy/MM/dd");
	}

	public static Date readDate(String prompt, String dateFormat) {
		do {
			try {
				final String strDate = readLine(prompt);
				final SimpleDateFormat df = new SimpleDateFormat(dateFormat);
				return df.parse(strDate);
			} catch (final ParseException ex) {
				// nothing to do
			}
		} while (true);
	}

	public static Calendar readCalendar(String prompt) {
		return readCalendar(prompt, "dd-MM-yyyy");
	}

	public static Calendar readCalendar(String prompt, String dateFormat) {
		do {
			try {
				final String strDate = readLine(prompt);
				final SimpleDateFormat df = new SimpleDateFormat(dateFormat);
				return DateTime.dateToCalendar(df.parse(strDate));
			} catch (final ParseException ex) {
				// nothing to do
			}
		} while (true);
	}

	public static double readDouble(String prompt) {
		do {
			try {
				final String input = readLine(prompt);
				return Double.parseDouble(input);
			} catch (final NumberFormatException ex) {
				// nothing to do
			}
		} while (true);
	}

	@SuppressWarnings("squid:S1166")
	public static void waitForKey(String prompt) {
		System.out.println(prompt);
		try {
			System.in.read();
		} catch (final IOException ex) {
			// nothing to do
		}
	}
}
