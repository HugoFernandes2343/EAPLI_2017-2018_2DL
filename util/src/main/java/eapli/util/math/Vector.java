/**
 *
 */
package eapli.util.math;

import java.util.Arrays;

/**
 * An immutable vector.
 *
 * @author Paulo Gandra Sousa
 *
 */
public class Vector {
    private final int numElems;
    private final double[] data;
    private final VectorType type;

    public Vector(double[] src, VectorType type) {
	this.numElems = src.length;
	this.type = type;
	this.data = Arrays.copyOf(src, this.numElems);
    }

    Vector(int numElems, VectorType type) {
	this.numElems = numElems;
	this.type = type;
	this.data = new double[numElems];
    }

    public Vector(Vector other) {
	this.numElems = other.numElems;
	this.type = other.type;
	this.data = Arrays.copyOf(other.data, other.numElems);
    }

    /* creates a "zero" vector with the same given value */
    public static Vector zero(int n, VectorType type, double zero) {
	final Vector z = new Vector(n, type);
	for (int i = 0; i < n; i++) {
	    z.putAt(i, zero);
	}
	return z;
    }

    // indexes are 1-based
    public double elementAt(int i) {
	return getAt(i - 1);
    }

    public Vector normalize() {
	double sum = 0;
	for (int i = 0; i < this.numElems; i++) {
	    sum += getAt(i);
	}
	final double denominator = java.lang.Math.sqrt(sum);

	final double[] norm = new double[this.numElems];
	for (int i = 0; i < this.numElems; i++) {
	    norm[i] = getAt(i) / denominator;
	}

	return new Vector(norm, this.type);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (obj.getClass() != Vector.class) {
	    return false;
	}

	final Vector other = (Vector) obj;
	if (this.numElems != other.numElems || this.type != other.type) {
	    return false;
	}
	for (int i = 0; i < this.numElems; i++) {
	    if (getAt(i) != other.getAt(i)) {
		return false;
	    }
	}
	return true;
    }

    public Vector add(Vector b) {
	if (this.numElems != b.numElems || this.type != b.type) {
	    throw new IllegalStateException();
	}

	final Vector c = new Vector(this.numElems, this.type);
	for (int i = 0; i < this.numElems; i++) {
	    c.putAt(i, getAt(i) + b.getAt(i));
	}
	return c;
    }

    public Vector subtract(Vector b) {
	if (this.numElems != b.numElems || this.type != b.type) {
	    throw new IllegalStateException();
	}

	final Vector c = new Vector(this.numElems, this.type);
	for (int i = 0; i < this.numElems; i++) {
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
    public double multiply(Vector B) {
	if (this.numElems != B.numElems || this.type != B.type) {
	    throw new IllegalStateException();
	}

	double accum = 0;
	for (int i = 0; i < this.numElems; i++) {
	    accum += (getAt(i) * B.getAt(i));
	}
	return accum;
    }

    // return a new vector obtained by multiplying a vector by a scalar
    public Vector scale(double k) {
	final Vector c = new Vector(this.numElems, this.type);
	for (int i = 0; i < this.numElems; i++) {
	    c.putAt(i, k * getAt(i));
	}
	return c;
    }

    public boolean isUnit() {
	double accum = 0;
	for (final double x : this.data) {
	    accum += (x * x);
	}
	return java.lang.Math.sqrt(accum) == 1.0;
    }

    // indexes are 0-based internal
    double getAt(int i) {
	return this.data[i];
    }

    void putAt(int i, double v) {
	this.data[i] = v;
    }

    public enum VectorType {
	ROW, COLUMN
    }
}
