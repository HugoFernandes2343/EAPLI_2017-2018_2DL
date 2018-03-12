/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class Math {

    private Math() {
	// to make sure this is an utility class
    }

    /**
     * Checks if a number is positive (greater than zero)
     *
     * @param number
     *            the number to check
     * @return true if number is positive
     */
    public static boolean isPositive(final long number) {
	return number > 0;
    }

    /**
     * determines if a number is odd.
     *
     * @param number
     *            the number to be tested
     * @return
     */
    public static boolean isOdd(final long number) {
	return number % 2 != 0;
    }

    /**
     * determines if a number is even.
     *
     * @param number
     *            the number to be tested
     * @return
     */
    public static boolean isEven(final long number) {
	return number % 2 == 0;
    }

    public static boolean between(long e, long begin, long end) {
	return e >= begin && e <= end;
    }

    public static <T extends Comparable<T>> boolean between(T e, T begin, T end) {
	return e.compareTo(begin) >= 0 && e.compareTo(end) <= 0;
    }

    /**
     * checks whether an int is prime or not.
     * http://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/
     *
     * @param number
     * @return
     */
    public static boolean isPrime(final long number) {
	if (isEven(number)) {
	    return false;
	}
	// if not, then just check the odds
	for (long i = 3; i * i <= number; i += 2) {
	    if (number % i == 0) {
		return false;
	    }
	}
	return true;
    }

    /**
     * Permite efectuar a conversão de um valor para outra escala.
     *
     * @param oldMin
     *            - exemplo 0
     * @param oldMax
     *            - exemplo 100
     * @param newMin
     *            - exemplo 0
     * @param newMax
     *            - exemplo 10
     * @param oldValue
     *            - exemplo 50
     * @return retorna o novo valor aplicando uma conversão linear - exemplo 5
     */
    @SuppressWarnings("squid:S1488")
    public static float simpleLinearConversion(final float oldMin, final float oldMax, final float newMin,
	    final float newMax, final float oldValue) {
	final float result = ((oldValue - oldMin) / (oldMax - oldMin)) * (newMax - newMin) + newMin;
	return result;
    }

    /**
     * Permite efectuar a conversão de um valor para outra escala.
     *
     * @param oldMin
     *            - exemplo 0
     * @param oldMax
     *            - exemplo 100
     * @param newMin
     *            - exemplo 0
     * @param newMax
     *            - exemplo 10
     * @param oldValue
     *            - exemplo 50
     * @return retorna o novo valor aplicando uma conversão linear - exemplo 5
     */
    @SuppressWarnings("squid:S1488")
    public static BigDecimal simpleLinearConversion(final BigDecimal oldMin, final BigDecimal oldMax,
	    final BigDecimal newMin, final BigDecimal newMax, final BigDecimal oldValue) {
	final BigDecimal a = oldValue.subtract(oldMin);
	final BigDecimal b = oldMax.subtract(oldMin);
	final BigDecimal c = a.divide(b, 1, RoundingMode.HALF_UP);
	final BigDecimal d = newMax.subtract(newMin);
	final BigDecimal result = c.multiply(d).add(newMin);
	return result;
    }

    public static String format(final BigDecimal amount) {
	// TODO use number formatter
	return amount.toString();
    }
}
