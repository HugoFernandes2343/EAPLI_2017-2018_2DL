/**
 *
 */
package eapli.framework.domain.time;

import java.util.Calendar;

import eapli.framework.domain.range.Range;

/**
 * A concrete range for Calendars.
 *
 * A time range is either a closed interval between time A and time B or a
 * closed interval starting at time A <i>ad infinitum<i>
 *
 * since this class inherits from a generic it cannot control how Calendar
 * objects will be persisted with JPA. see TimeInterval and DateInterval
 *
 * @author Paulo Gandra Sousa
 *
 */
public class TimeRange extends Range<Calendar> {

	private static final long serialVersionUID = 1L;

	/**
	 * creates a closed time interval on both ends
	 *
	 * @param begin
	 * @param end
	 */
	public TimeRange(Calendar begin, Calendar end) {
		super(begin, BoundaryLimitType.CLOSED, end, BoundaryLimitType.CLOSED);
	}

	/**
	 * creates a time interval from begin until "infinity"
	 *
	 * @param begin
	 * @param end
	 */
	public TimeRange(Calendar begin) {
		super(begin, BoundaryLimitType.CLOSED, null, BoundaryLimitType.INFINITY);
	}

	protected TimeRange() {
	}
}
