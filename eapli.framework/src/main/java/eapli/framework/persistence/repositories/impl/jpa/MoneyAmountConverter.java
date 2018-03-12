package eapli.framework.persistence.repositories.impl.jpa;

import java.math.BigInteger;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * To avoid breaking the information hiding principle, we convert the value
 * stored in cents when saving it to the store and divide it back again when
 * reading it.
 *
 * in this case we need to know that the actual value stored in the Money object
 * is stored in cents, so we must first multiply it when reading it from the
 * persistence store. the JPA mapping already forces us to know the details of
 * the class so the information hiding was broken from the beginning
 *
 * @author Paulo Gandra de Sousa
 *
 */
@Converter
public class MoneyAmountConverter implements AttributeConverter<BigInteger, BigInteger> {

    private static final BigInteger HUNDRED = BigInteger.valueOf(100);

    @Override
    public BigInteger convertToDatabaseColumn(final BigInteger arg0) {
	return arg0.multiply(HUNDRED);
    }

    @Override
    public BigInteger convertToEntityAttribute(final BigInteger arg0) {
	return arg0.divide(HUNDRED);
    }
}
