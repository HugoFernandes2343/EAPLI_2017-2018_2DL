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
public class GenericDtoForInheritedClassTest {

    private static String STRING_FIELD_VALUE = "abc";
    private static int INT_FIELD_VALUE = 3;
    private static float FLOAT_FIELD_VALUE = 3.1415f;

    private static final SimpleClass subject = new SimpleClass(STRING_FIELD_VALUE, INT_FIELD_VALUE, FLOAT_FIELD_VALUE);
    private static GenericDTO instance;

    public GenericDtoForInheritedClassTest() {
    }

    @BeforeClass
    public static void setUpClass() {
	System.out.println("GenericDtoForInheritedClassTest");

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
    private static class SimpleClassBase {
	private final String stringField;
	private final int intField;

	public SimpleClassBase(String s, int i) {
	    this.stringField = s;
	    this.intField = i;
	}
    }

    @SuppressWarnings("unused")
    private static class SimpleClass extends SimpleClassBase {
	private final float floatField;

	public SimpleClass(String s, int i, float f) {
	    super(s, i);
	    this.floatField = f;
	}
    }

    @Test
    public void ensureType() {
	System.out.println("ensureType");

	assertEquals("Name of type is incorrect", instance.type(), subject.getClass().getName());
    }

    @Test
    public void ensureDTOHas3Fields() {
	System.out.println("ensureDTOHas3Fields");

	assertEquals("Name of type is incorrect", 3, instance.size());
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

    @Test
    public void ensureFloatFieldIsTransformed() {
	System.out.println("ensureFloatFieldIsTransformed");

	assertEquals("'floatField' is incorrectly transformed", FLOAT_FIELD_VALUE, instance.get("floatField"));
    }
}
