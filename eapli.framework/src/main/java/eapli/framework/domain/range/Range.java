/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain.range;

import java.io.Serializable;

import javax.persistence.Embeddable;

import eapli.framework.domain.ddd.ValueObject;

/**
 * a generic immutable range class, i.e., a continuous domain.
 *
 * @author Paulo Gandra Sousa
 * @param <T>
 */
@Embeddable
public class Range<T extends Comparable<T> & Serializable> implements ValueObject {

	protected enum BoundaryLimitType {
		INFINITY, OPEN, CLOSED
	}

	private static final long serialVersionUID = 1L;
	private T start;
	private T end;
	private BoundaryLimitType startBoundary;
	private BoundaryLimitType endBoundary;

	/**
	 * The builder of ranges
	 *
	 * @author pgsou_000
	 *
	 * @param <R>
	 */
	public static class RangeBuilder<R extends Comparable<R> & Serializable> {
		private final R start;
		private R end;
		private final BoundaryLimitType startBoundary;
		private BoundaryLimitType endBoundary;

		/**
		 * starts building a range at start
		 *
		 * @param start
		 * @param startBoundary
		 */
		private RangeBuilder(R start, BoundaryLimitType startBoundary) {
			assert (startBoundary == BoundaryLimitType.INFINITY && start == null)
					|| (startBoundary != BoundaryLimitType.INFINITY && start != null);
			this.start = start;
			this.startBoundary = startBoundary;
		}

		public RangeBuilder<R> closedTo(R anchor) {
			this.end = anchor;
			this.endBoundary = BoundaryLimitType.CLOSED;
			return this;
		}

		public RangeBuilder<R> openTo(R anchor) {
			this.end = anchor;
			this.endBoundary = BoundaryLimitType.OPEN;
			return this;
		}

		public RangeBuilder<R> toInfinity() {
			this.end = null;
			this.endBoundary = BoundaryLimitType.INFINITY;
			return this;
		}

		public Range<R> build() {
			return new Range<>(this.start, this.startBoundary, this.end, this.endBoundary);
		}
	}

	protected Range() {
		// for ORM
	}

	/**
	 * constructs a range.
	 *
	 * @param start
	 *            anchor start of the range or null to represent infinity
	 * @param end
	 *            anchor end of the range or null to represent infinity
	 * @param startBoundary
	 *            indicates if the range is open or closed at the start anchor
	 * @param endBoundary
	 *            indicates if the range is open or closed at the end anchor
	 */
	protected Range(T start, BoundaryLimitType startBoundary, T end, BoundaryLimitType endBoundary) {
		if ((start == null && startBoundary != BoundaryLimitType.INFINITY)
				|| (end == null && endBoundary != BoundaryLimitType.INFINITY)) {
			throw new IllegalStateException("start or end must be non-null");
		}

		if (end != null && start != null && end.compareTo(start) < 0) {
			throw new IllegalStateException("The end value of a range must be bigger than its start");
		}
		if (end != null && start != null && end.compareTo(start) == 0
				&& (startBoundary == BoundaryLimitType.OPEN || endBoundary == BoundaryLimitType.OPEN)) {
			throw new IllegalStateException("An empty range is not allowed");
		}

		this.start = start;
		this.end = end;
		this.startBoundary = startBoundary;
		this.endBoundary = endBoundary;
	}

	/**
	 * A factory method for ranges that start at "negative infinity"
	 *
	 * @return a builder
	 */
	public static <T extends Comparable<T> & Serializable> RangeBuilder<T> fromInfinity() {
		return new RangeBuilder<>(null, BoundaryLimitType.INFINITY);
	}

	/**
	 * A factory method for closed ranges that start at a specific anchor point
	 *
	 * @return a builder
	 */
	public static <T extends Comparable<T> & Serializable> RangeBuilder<T> closedFrom(T start) {
		return new RangeBuilder<>(start, BoundaryLimitType.CLOSED);
	}

	/**
	 * A factory method for open ranges that start at a specific anchor point
	 *
	 * @return a builder
	 */
	public static <T extends Comparable<T> & Serializable> RangeBuilder<T> openFrom(T start) {
		return new RangeBuilder<>(start, BoundaryLimitType.OPEN);
	}

	/**
	 * checks if a value belongs in this range
	 *
	 * @param target
	 * @return
	 */
	public boolean includes(T target) {
		if (this.startBoundary != BoundaryLimitType.INFINITY && this.endBoundary != BoundaryLimitType.INFINITY) {
			return regularIncludes(target);
		} else if (this.endBoundary == BoundaryLimitType.INFINITY) {
			return toInfinityRangeIncludes(target);
		} else {
			assert this.startBoundary == BoundaryLimitType.INFINITY;
			return fromInfinityRangeIncludes(target);
		}
	}

	private boolean fromInfinityRangeIncludes(T target) {
		if (target.compareTo(this.end) > 0) {
			return false;
		}
		return !(this.endBoundary == BoundaryLimitType.OPEN && target.compareTo(this.end) == 0);
	}

	private boolean toInfinityRangeIncludes(T target) {
		if (target.compareTo(this.start) < 0) {
			return false;
		}
		return !(this.startBoundary == BoundaryLimitType.OPEN && target.compareTo(this.start) == 0);
	}

	private boolean regularIncludes(T target) {
		if (target.compareTo(this.start) < 0 || target.compareTo(this.end) > 0) {
			return false;
		}
		if (this.startBoundary == BoundaryLimitType.OPEN && target.compareTo(this.start) == 0) {
			return false;
		}
		return !(this.endBoundary == BoundaryLimitType.OPEN && target.compareTo(this.end) == 0);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(startBracket(this.startBoundary == BoundaryLimitType.OPEN));
		sb.append(rangeValue(this.isFromInfinity(), this.start));
		sb.append(", ");
		sb.append(rangeValue(this.isToInfinity(), this.end));
		sb.append(endBracket(this.endBoundary == BoundaryLimitType.OPEN));
		return sb.toString();
	}

	public boolean isToInfinity() {
		return this.endBoundary == BoundaryLimitType.INFINITY;
	}

	public boolean isFromInfinity() {
		return this.startBoundary == BoundaryLimitType.INFINITY;
	}

	public T start() {
		return this.start;
	}

	public T end() {
		return this.end;
	}

	private char startBracket(boolean isOpen) {
		if (isOpen) {
			return ']';
		} else {
			return '[';
		}
	}

	private char endBracket(boolean isOpen) {
		if (isOpen) {
			return '[';
		} else {
			return ']';
		}
	}

	private String rangeValue(boolean isInfinity, T value) {
		if (isInfinity) {
			return "oo";
		} else {
			return value.toString();
		}
	}

	public boolean intersects(Range<T> other) {
		throw new UnsupportedOperationException();
	}

	public Range<T> intersection(Range<T> other) {
		throw new UnsupportedOperationException();
	}

	public Range<T> union(Range<T> other) {
		throw new UnsupportedOperationException();
	}
}
