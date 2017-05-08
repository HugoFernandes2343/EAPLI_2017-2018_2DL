/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.framework.dto;

import static org.junit.Assert.assertEquals;

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
public class GenericDtoForSimpleClassTest {

    private static String STRING_FIELD_VALUE = "abc";
    private static int INT_FIELD_VALUE = 3;
    private static final SimpleClass subject = new SimpleClass(STRING_FIELD_VALUE, INT_FIELD_VALUE);
    private static GenericDTO instance;

    public GenericDtoForSimpleClassTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("GenericDtoForSimpleClassTest");

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

    @Test
    public void ensureType() {
	System.out.println("ensureType");

	assertEquals("Name of type is incorrect", subject.getClass().getName(), instance.type());
    }

    @Test
    public void ensureDTOHas2Fields() {
	System.out.println("ensureDTOHas2Fields");

	assertEquals("Name of type is incorrect", 2, instance.size());
    }

    @Test
    public void ensureStringFieldIsTransformed() {
	System.out.println("ensureStringFieldIsTransformed");

	assertEquals("'stringField' is incorrectly transformed", STRING_FIELD_VALUE, instance.get("stringField"));
    }

    @Test
    public void ensureIntFieldIsTransformed() {
	System.out.println("ensureIntFieldIsTransformed");

	assertEquals("'intField' is incorrectly transformed", INT_FIELD_VALUE, instance.get("intField"));
    }
}
