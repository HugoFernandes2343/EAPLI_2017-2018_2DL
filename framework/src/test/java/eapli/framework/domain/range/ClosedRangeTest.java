/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain.range;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eapli.framework.domain.range.Range;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class ClosedRangeTest extends AbstractRangeTest {

    public ClosedRangeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("closedRange");
	instance = Range.closedFrom(START).closedTo(END).build();
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

    @Test
    public void ensureStartIsInRange() {
	System.out.println("ensureStartIsInRange");
	final Long target = new Long(START_VALUE);
	final boolean result = instance.includes(target);
	assertTrue("start must be part of a closed range", result);
    }

    @Test
    public void ensureEndIsInRange() {
	System.out.println("ensureEndIsInRange");
	final Long target = new Long(END_VALUE);
	final boolean result = instance.includes(target);
	assertTrue("end must be part of a closed range", result);
    }
}
