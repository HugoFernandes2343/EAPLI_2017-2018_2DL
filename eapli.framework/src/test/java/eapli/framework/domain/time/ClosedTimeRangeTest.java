package eapli.framework.domain.time;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eapli.framework.util.DateTime;

public class ClosedTimeRangeTest {

	private static final Calendar START = DateTime.newCalendar(2017, 05, 01);
	private static final Calendar END = DateTime.newCalendar(2017, 05, 15);
	private static TimeRange INSTANCE = new TimeRange(START, END);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void ensureIncludesStart() {
		assertTrue("Start is not included", INSTANCE.includes(START));
	}

	@Test
	public void ensureIncludesEnd() {
		assertTrue("End is not included", INSTANCE.includes(END));
	}

	@Test
	public void ensureIncludesMid() {
		final Calendar mid = (Calendar) START.clone();
		mid.add(Calendar.DATE, 2);
		assertTrue("Mid is not included", INSTANCE.includes(mid));
	}

	@Test
	public void ensureDoesntIncludeBeforeStart() {
		final Calendar before = (Calendar) START.clone();
		before.add(Calendar.DATE, -2);
		assertFalse("Mid is not included", INSTANCE.includes(before));
	}

	@Test
	public void ensureDoesntIncludeAfterEnd() {
		final Calendar after = (Calendar) END.clone();
		after.add(Calendar.DATE, 2);
		assertFalse("Mid is not included", INSTANCE.includes(after));
	}
}
