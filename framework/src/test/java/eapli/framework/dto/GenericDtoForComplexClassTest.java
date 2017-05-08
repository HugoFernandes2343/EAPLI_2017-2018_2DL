/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class GenericDtoForComplexClassTest {

    private static String STRING_FIELD_VALUE_1 = "abc";
    private static int INT_FIELD_VALUE_1 = 3;
    private static String STRING_FIELD_VALUE_2 = "xyz";
    private static int INT_FIELD_VALUE_2 = 9;
    private static final SimpleClass childOne = new SimpleClass(STRING_FIELD_VALUE_1, INT_FIELD_VALUE_1);
    private static final SimpleClass childTwo = new SimpleClass(STRING_FIELD_VALUE_2, INT_FIELD_VALUE_2);
    private static float FLOAT_FIELD_VALUE = 3.1415f;
    private static final ComplexClass subject = new ComplexClass(childOne, childTwo, FLOAT_FIELD_VALUE);

    private static GenericDTO instance;

    public GenericDtoForComplexClassTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("GenericDtoForComplexClassTest");

	instance = GenericDTO.of(subject);

	System.out.println("===========");
	System.out.println(instance);
	System.out.println(instance.type());
	for (final Map.Entry<String, Object> e : instance.entrySet()) {
	    System.out.println("[" + e.getKey() + "] => [" + e.getValue() + "]");
	}
	System.out.println("===========");
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

    @SuppressWarnings("unused")
    private static class SimpleClass {
	private final String stringField;
	private final int intField;

	public SimpleClass(String s, int i) {
	    this.stringField = s;
	    this.intField = i;
	}
    }

    @SuppressWarnings("unused")
    private static class ComplexClass {
	SimpleClass one;
	SimpleClass two;
	SimpleClass sameAsOne;
	float floatField;

	public ComplexClass(SimpleClass a, SimpleClass b, float f) {
	    this.one = a;
	    this.two = b;
	    this.sameAsOne = a;
	    this.floatField = f;
	}
    }

    @Test
    public void ensureType() {
	System.out.println("ensureType");

	assertEquals("Name of type is incorrect", subject.getClass().getName(), instance.type());
    }

    @Test
    public void ensureDTOHas4Fields() {
	System.out.println("ensureDTOHas2Fields");

	assertEquals("Name of type is incorrect", 4, instance.size());
    }

    @Test
    public void ensureChildOneIsDTO() {
	System.out.println("ensureChildIsDTO");

	assertTrue("'one' is incorrectly transformed", instance.get("one") instanceof GenericDTO);
    }

    @Test
    public void ensureChildTwoIsDTO() {
	System.out.println("ensureChildTwoIsDTO");

	assertTrue("'two' is incorrectly transformed", instance.get("two") instanceof GenericDTO);
    }

    @Test
    public void ensureChildSameAsOneIsDTO() {
	System.out.println("ensureChildSameAsOneIsDTO");

	assertTrue("'sameAsOne' is incorrectly transformed", instance.get("sameAsOne") instanceof GenericDTO);
    }

    @Test
    public void ensureFloatFieldIsTransformed() {
	System.out.println("ensureFloatFieldIsTransformed");

	assertEquals("'floatField' is incorrectly transformed", FLOAT_FIELD_VALUE, instance.get("floatField"));
    }
}
