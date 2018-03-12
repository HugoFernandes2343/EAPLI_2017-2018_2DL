/**
 *
 */
package eapli.framework.domain.time;

import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import eapli.framework.domain.ddd.ValueObject;

/**
 * A persistent time period. Both the date and the time part of calendar are
 * relevant
 *
 * @author Paulo Gandra Sousa
 *
 */
@Embeddable
public class TimeInterval implements ValueObject {

    private static final long serialVersionUID = -4658136141745243778L;

    @Temporal(TemporalType.TIMESTAMP)
    private final Calendar start;

    @Temporal(TemporalType.TIMESTAMP)
    private final Calendar end;

    @Transient
    private final TimeRange period;

    /**
     * Constructs a closed time interval
     *
     * @param start
     * @param end
     */
    public TimeInterval(Calendar start, Calendar end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException();
        }
        this.start = start;
        this.end = end;
        period = new TimeRange(start, end);
    }

    protected TimeInterval() {
        // ORM
        start = end = null;
        period = null;
    }

    public Calendar start() {
        return start;
    }

    public Calendar end() {
        return start;
    }

    public boolean includes(Calendar target) {
        return period.includes(target);
    }
}
