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
public class ToInfinityOpenRangeTest extends AbstractRangeTest {

    public ToInfinityOpenRangeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("ToInfinityOpenRange");
	instance = Range.openFrom(START).toInfinity().build();
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
    public void ensureStartIsNotInRange() {
	System.out.println("ensureStartIsNotInRange");
	final Long target = new Long(START_VALUE);
	final boolean result = instance.includes(target);
	assertFalse("start cannot be part of an open range", result);
    }

    @Test
    public void ensureBigValueIsIncluded() {
	System.out.println("ensureBigValueIsIncluded");
	final Long target = Long.MAX_VALUE;
	final boolean result = instance.includes(target);
	assertTrue("to infinity range must include any value bigger than start", result);
    }

    @Test
    public void ensureIsToInfinity() {
	System.out.println("ensureIsToInfinity");
	final boolean result = instance.isToInfinity();
	assertTrue("to inifinity ranges must be to infinity", result);
    }
}
