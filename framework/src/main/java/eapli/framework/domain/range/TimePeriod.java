/**
 *
 */
package eapli.framework.domain.range;

import java.util.Calendar;

import javax.persistence.Embeddable;

/**
 * A specific range for time intervals.
 *
 * @author Paulo Gandra Sousa
 *
 */
@Embeddable
public class TimePeriod extends Range<Calendar> {

    private static final long serialVersionUID = 1L;

    /**
     * creates an open time interval on both ends
     *
     * @param begin
     * @param end
     */
    public TimePeriod(Calendar begin, Calendar end) {
	super(begin, BoundaryLimitType.OPEN, end, BoundaryLimitType.OPEN);
    }

    /**
     * creates a time interval from begin until "infinity"
     *
     * @param begin
     * @param end
     */
    public TimePeriod(Calendar begin) {
	super(begin, BoundaryLimitType.CLOSED, null, BoundaryLimitType.INFINITY);
    }

    protected TimePeriod() {
    }
}
