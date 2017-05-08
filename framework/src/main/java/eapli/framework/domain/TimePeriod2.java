/**
 *
 */
package eapli.framework.domain;

import java.util.Calendar;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A simple time period class that does not use Range. does not possess any
 * business logic; it is just a data bag.
 *
 * @author Paulo Gandra Sousa
 *
 */
@Embeddable
public class TimePeriod2 {

    @Temporal(TemporalType.DATE)
    Calendar start;

    @Temporal(TemporalType.DATE)
    Calendar end;

    /**
     * Constructor
     *
     * @param start
     * @param end
     */
    public TimePeriod2(Calendar start, Calendar end) {
	this.start = start;
	this.end = end;
    }

    protected TimePeriod2() {
    }

    public Calendar start() {
	return this.start;
    }

    public Calendar end() {
	return this.start;
    }
}
