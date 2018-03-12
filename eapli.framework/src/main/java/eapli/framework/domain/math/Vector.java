/**
 *
 */
package eapli.framework.domain.math;

import java.util.Arrays;

import eapli.framework.domain.ddd.ValueObject;

/**
 * An immutable vector.
 *
 * Vector indexes are 1-based, so a Vector with length 4 will have indexes 1, 2,
 * 3 and 4
 *
 * @FIXME this class uses double. should provably switch to BigDecimal...
 *
 * @author Paulo Gandra Sousa
 *
 */
public class Vector implements ValueObject {

    private static final long serialVersionUID = -3010865637192089056L;

    public enum VectorType {
	ROW, COLUMN
    }

    private final int numElems;
    private final double[] data;
    private final VectorType type;

    public Vector(final double[] src, final VectorType type) {
	numElems = src.length;
	this.type = type;
	data = Arrays.copyOf(src, numElems);
    }

    Vector(final int numElems, final VectorType type) {
	this.numElems = numElems;
	this.type = type;
	data = new double[numElems];
    }

    public Vector(final Vector other) {
	numElems = other.numElems;
	type = other.type;
	data = Arrays.copyOf(other.data, other.numElems);
    }

    /**
     * creates a vector with all elements with the value 0.0
     */
    public static Vector zero(final int n, final VectorType type) {
	return zero(n, type, 0.0);
    }

    /**
     * creates a "zero" vector with the same given value
     */
    public static Vector zero(final int n, final VectorType type, final double zero) {
	final Vector z = new Vector(n, type);
	for (int i = 0; i < n; i++) {
	    z.putAt(i, zero);
	}
	return z;
    }

    // indexes are 1-based
    public double elementAt(final int i) {
	return getAt(i - 1);
    }

    public Vector normalized() {
	double sum = 0;
	for (int i = 0; i < numElems; i++) {
	    sum += getAt(i);
	}
	final double denominator = java.lang.Math.sqrt(sum);

	final double[] norm = new double[numElems];
	for (int i = 0; i < numElems; i++) {
	    norm[i] = getAt(i) / denominator;
	}

	return new Vector(norm, type);
    }

    @Override
    public boolean equals(final Object obj) {
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof Vector)) {
	    return false;
	}

	final Vector other = (Vector) obj;
	if (numElems != other.numElems || type != other.type) {
	    return false;
	}
	for (int i = 0; i < numElems; i++) {
	    if (getAt(i) != other.getAt(i)) {
		return false;
	    }
	}
	return true;
    }

    public Vector add(final Vector b) {
	if (numElems != b.numElems || type != b.type) {
	    throw new IllegalArgumentException();
	}

	final Vector c = new Vector(numElems, type);
	for (int i = 0; i < numElems; i++) {
	    c.putAt(i, getAt(i) + b.getAt(i));
	}
	return c;
    }

    public Vector subtract(final Vector b) {
	if (numElems != b.numElems || type != b.type) {
	    throw new IllegalArgumentException();
	}

	final Vector c = new Vector(numElems, type);
	for (int i = 0; i < numElems; i++) {
	    c.putAt(i, getAt(i) - b.getAt(i));
	}
	return c;
    }

    /**
     * calculates the dot product of two vectors
     *
     * @param B
     * @return the dot product of two vectors
     */
    public double multiply(final Vector B) {
	if (numElems != B.numElems || type != B.type) {
	    throw new IllegalArgumentException();
	}

	double accum = 0;
	for (int i = 0; i < numElems; i++) {
	    accum += (getAt(i) * B.getAt(i));
	}
	return accum;
    }

    // return a new vector obtained by multiplying a vector by a scalar
    public Vector scale(final double k) {
	final Vector c = new Vector(numElems, type);
	for (int i = 0; i < numElems; i++) {
	    c.putAt(i, k * getAt(i));
	}
	return c;
    }

    public boolean isUnit() {
	double accum = 0;
	for (final double x : data) {
	    accum += (x * x);
	}
	return java.lang.Math.sqrt(accum) == 1.0;
    }

    // indexes are 0-based
    /* package */ double getAt(final int i) {
	return data[i];
    }

    // indexes are 0-based
    /* package */ void putAt(final int i, final double v) {
	data[i] = v;
    }
}
