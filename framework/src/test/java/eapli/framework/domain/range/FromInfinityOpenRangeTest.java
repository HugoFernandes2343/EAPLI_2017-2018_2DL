/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.domain.range;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class FromInfinityOpenRangeTest extends AbstractRangeTest {

    public FromInfinityOpenRangeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("FromInfinityOpenRangeTest");
	final Range.RangeBuilder<Long> b = Range.fromInfinity();
	instance = b.openTo(END).build();
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
    public void ensureEndIsNotInRange() {
	System.out.println("ensureEndIsNotInRange");
	final Long target = new Long(END_VALUE);
	final boolean result = instance.includes(target);
	assertFalse("end cannot be part of an open range", result);
    }

    @Test
    public void ensureSmallValueIsIncluded() {
	System.out.println("ensureSmallValueIsIncluded");
	final Long target = Long.MIN_VALUE;
	final boolean result = instance.includes(target);
	assertTrue("From infinity range must include any value smaller than end", result);
    }

    @Test
    public void ensureIsFromInfinity() {
	System.out.println("ensureIsFromInfinity");
	final boolean result = instance.isFromInfinity();
	assertTrue("from infinity ranges must be from infinity", result);
    }
}
