/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class DateTimeTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of weekNumber method, of class DateTime.
     */
    @Test
    public void testJanuaryFirst2014IsWeekOne() {
	System.out.println("weekNumber");
	// TODO do not use own methods whcih have not been tested yet
	final Calendar date = DateTime.newCalendar(2014, 01, 01);
	final int expResult = 1;
	final int result = DateTime.weekNumber(date);
	assertEquals(expResult, result);
    }

    /**
     * Test of weekNumber method, of class DateTime.
     */
    @Test
    public void testMarch21st2014IsWeek12() {
	System.out.println("weekNumber");
	// TODO do not use own methods whcih have not been tested yet
	final Calendar date = DateTime.newCalendar(2014, 03, 21);
	final int expResult = 12;
	final int result = DateTime.weekNumber(date);
	assertEquals(expResult, result);
    }

    /**
     * Test of beginningOfWeek method, of class DateTime.
     */
    @Ignore
    @Test
    public void testFirstDayOfWeek13Of2014Is16() {
	System.out.println("beginningOfWeek");
	final int year = 2014;
	final int week = 13;
	final Calendar expResult = DateTime.newCalendar(2014, 03, 16);
	final Calendar result = DateTime.beginningOfWeek(year, week);
	assertEquals(expResult, result);
    }

    /**
     * Test of endOfWeek method, of class DateTime.
     */
    @Ignore
    @Test
    public void testLastDayOfWeek13Of2014Is22() {
	System.out.println("endOfWeek");
	final int year = 2014;
	final int week = 13;
	final Calendar expResult = DateTime.newCalendar(2014, 03, 22);
	final Calendar result = DateTime.endOfWeek(year, week);
	assertEquals(expResult, result);
    }

    /**
     * Test of endOfMonth method, of class DateTime.
     */
    @Test
    public void testLastDayOfMarch2014is31() {
	System.out.println("endOfMonth");
	final Calendar reference = DateTime.newCalendar(2014, 03, 21);
	final Calendar expResult = DateTime.newCalendar(2014, 03, 31);
	final Calendar result = DateTime.endOfMonth(reference);
	assertEquals(expResult, result);
    }

    /**
     * Test of endOfMonth method, of class DateTime.
     */
    @Test
    public void testLastDayOfFebruary2014is28() {
	System.out.println("endOfMonth");
	final Calendar reference = DateTime.newCalendar(2014, 02, 21);
	final Calendar expResult = DateTime.newCalendar(2014, 02, 28);
	final Calendar result = DateTime.endOfMonth(reference);
	assertEquals(expResult, result);
    }

    /**
     * Test of endOfMonth method, of class DateTime.
     */
    @Test
    public void testLastDayOfFebruary2012is29() {
	System.out.println("endOfMonth");
	final Calendar reference = DateTime.newCalendar(2012, 02, 21);
	final Calendar expResult = DateTime.newCalendar(2012, 02, 29);
	final Calendar result = DateTime.endOfMonth(reference);
	assertEquals(expResult, result);
    }

    /**
     * Test of parseDate method, of class DateTime.
     */
    @Ignore
    @Test
    public void testParseDate_String_String() {
	System.out.println("parseDate");
	final String aDateString = "2014-03-21";
	final String format = "YYYY-MM-DD";
	final Calendar expResult = DateTime.newCalendar(2014, 03, 21);
	final Calendar result = DateTime.parseDate(aDateString, format);
	assertEquals(expResult, result);
    }
}
