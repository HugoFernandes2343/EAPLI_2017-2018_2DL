package eapli.framework.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Currency;

import javax.persistence.Embeddable;

import eapli.framework.domain.ddd.ValueObject;

/**
 * represents money values.
 *
 * based on http://martinfowler.com/eaaDev/quantity.html
 *
 */
@Embeddable
public class Money implements Comparable<Money>, Serializable, ValueObject {

    private static final long serialVersionUID = 1L;
    private static final transient int HUNDRED = 100;
    private final BigInteger amount;
    private final Currency currency;

    /**
     * For ORM tool only
     */
    protected Money() {
	// for ORM tool only
	this.amount = null;
	this.currency = null;
    }

    /**
     * Constructs a new Money object.
     *
     * You'll notice there are no setters. Money is a Value Object and is thus
     * immutable. It helps to have a variety of constructors to make it easy to
     * make monies. Constructors that convert from basic numeric types are
     * always useful.
     *
     * @param amount
     * @param currency
     */
    public Money(double amount, Currency currency) {
	this.amount = BigInteger.valueOf(Math.round(amount * HUNDRED));
	this.currency = currency;
    }

    public Money(long amount, Currency currency) {
	this.amount = BigInteger.valueOf(amount * HUNDRED);
	this.currency = currency;
    }

    /**
     *
     * @param amountInPennies
     * @param currency
     * @param privacyMarker
     *            just to differentiate between two private constructors
     */
    @SuppressWarnings("squid:S1172")
    private Money(BigInteger amountInPennies, Currency currency, boolean privacyMarker) {
	assert amountInPennies != null;
	assert currency != null;

	// FIXME do validations
	this.amount = amountInPennies;
	this.currency = currency;
    }

    /**
     * If you use one currency a lot, you may want a special constructor for
     * that currency.
     */
    public static Money dollars(final double amount) {
	return new Money(amount, Currency.getInstance("USD"));
    }

    /**
     * If you use one currency a lot, you may want a special constructor for
     * that currency.
     */
    public static Money euros(final double amount) {
	return new Money(amount, Currency.getInstance("EUR"));
    }

    /**
     * Returns the amount portion of this Money object.
     *
     * Notice that I use a BigInteger. In Java I could equally well use a
     * BigDecimal, but in many languages an integer is the only decent option,
     * so using an integer seems the best for explanation. Don't be afraid to
     * choose your representation of the amount part of a quantity based on
     * performance factors. The beauty of objects is that you can choose any
     * data structure you like on the inside, providing you hide it on the
     * outside.
     */
    public double amount() {
	return this.amount.doubleValue() / HUNDRED;
    }

    public BigDecimal amountAsDecimal() {
	return BigDecimal.valueOf(amount());
    }

    /**
     * Returns the currency of this Money object.
     *
     * @return
     */
    public Currency currency() {
	return this.currency;
    }

    /**
     * Adds two Money objects and returns the result as a new object.
     *
     * For addition and subtraction I'm not trying to do any fancy conversion.
     * Notice that I'm using a special constructor with a marker argument.
     */
    public Money add(Money arg) {
	if (!this.currency.equals(arg.currency)) {
	    throw new IllegalArgumentException("Cannot add different currencies");
	}
	return new Money(this.amount.add(arg.amount), this.currency, true);
    }

    /**
     * Subtracts two Money instances and returns a third one with the result.
     *
     * @param arg
     * @return
     */
    public Money subtract(Money arg) {
	return add(arg.negate());
    }

    public Money negate() {
	return new Money(this.amount.negate(), this.currency, true);
    }

    /**
     * Multiplies two Money objects and returns the result a new object.
     *
     */
    public Money multiply(double arg) {
	return new Money(amount() * arg, this.currency);
    }

    /**
     * Divides this Money object by a certain integer denominator doing the
     * right allocation of cents.
     *
     * Multiplication is very straightforward. But division is not, as we have
     * to take care of errant pennies. We'll do that by returning an array of
     * monies, such that the sum of the array is equal to the original amount,
     * and the original amount is distributed fairly between the elements of the
     * array. Fairly in this sense means those at the begining get the extra
     * pennies.
     */
    public Money[] divide(int denominator) {
	final BigInteger bigDenominator = BigInteger.valueOf(denominator);
	final Money[] result = new Money[denominator];
	final BigInteger simpleResult = this.amount.divide(bigDenominator);
	for (int i = 0; i < denominator; i++) {
	    result[i] = new Money(simpleResult, this.currency, true);
	}
	final int remainder = this.amount.subtract(simpleResult.multiply(bigDenominator)).intValue();
	for (int i = 0; i < remainder; i++) {
	    result[i] = result[i].add(new Money(BigInteger.valueOf(1), this.currency, true));
	}
	return result;
    }

    /**
     * Compares two Money objects.
     *
     * Next we'll look at comparing monies, in Java the approach is to implement
     * comparable.
     */
    @Override
    public int compareTo(Money arg) {
	if (!this.currency.equals(arg.currency)) {
	    throw new IllegalArgumentException("Cannot add different currencies");
	}
	return this.amount.compareTo(arg.amount);
    }

    /**
     * Compares two Money objects.
     *
     * It's also useful to provide some better named operations such as: That
     * makes methods that need the comparison much easier to read.
     */
    public boolean greaterThan(Money arg) {
	return compareTo(arg) > 0;
    }

    /**
     * Compares two Money objects.
     */
    public boolean lessThan(Money arg) {
	return compareTo(arg) < 0;
    }

    /**
     * Compares two Money objects.
     */
    public boolean greaterThanOrEqual(Money arg) {
	return compareTo(arg) >= 0;
    }

    /**
     * Compares two Money objects.
     */
    public boolean lessThanOrEqual(Money arg) {
	return compareTo(arg) <= 0;
    }

    /**
     * Since money is a value, it should override equals.
     */
    @Override
    public boolean equals(Object arg) {
	if (!(arg instanceof Money)) {
	    return false;
	}
	final Money other = (Money) arg;
	return this.currency.equals(other.currency) && this.amount.equals(other.amount);
    }

    /**
     * Since you override equals, don't forget to also override hash (here's a
     * simple suggestion for that).
     */
    @Override
    public int hashCode() {
	return this.amount.hashCode();
    }

    public int signum() {
	return this.amount.signum();
    }

    @Override
    public String toString() {
	return amount() + " " + currency();
    }
}
